package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.domain.ShortUrl;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Service
public class UrlCacheImpl implements UrlCache {

    private static final String DEFAULT_PATH = "/";

    private final Map<String, String> urls = new HashMap<>(20);

    @Override
    public String resolveUrl(final String shortUrl) {
        return this.urls.getOrDefault(shortUrl, UrlCacheImpl.DEFAULT_PATH);
    }

    @Override
    public void put(final FullUrl url) {
        Map<String, String> newUrls = url.shortUrls()
            .stream()
            .collect(Collectors.toMap(ShortUrl::getUrl, shortUrl -> url.getUrl()));

        this.urls.putAll(newUrls);
    }

    @Override
    public void putAll(final Collection<FullUrl> urls) {

        Map<String, String> newUrls = urls.stream()
            .flatMap(fullUrl -> fullUrl.shortUrls().stream())
            .collect(Collectors.toMap(ShortUrl::getUrl, ShortUrl::fullUrl));

        this.urls.putAll(newUrls);
    }
}
