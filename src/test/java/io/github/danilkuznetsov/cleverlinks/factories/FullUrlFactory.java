package io.github.danilkuznetsov.cleverlinks.factories;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.domain.ShortUrl;
import java.util.HashSet;
import java.util.Set;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public interface FullUrlFactory {

    Long FIRST_URL_ID = 1L;
    String FIRST_URL = "http://google.com";

    static FullUrl fullUrl() {

        final FullUrl url = FullUrlFactory.simpleFullUrl();

        FullUrlFactory.safeAddShortUrl(url, ShortUrlFactory.SHORT_URL);

        return url;
    }

    static FullUrl simpleFullUrl() {

        return FullUrl
            .builder()
            .id(FullUrlFactory.FIRST_URL_ID)
            .url(FullUrlFactory.FIRST_URL)
            .build();
    }

    static void safeSetId(FullUrl targetUrl, Long id) {
        ReflectionTestUtils.setField(targetUrl, "id", id);
    }

    static void safeAddShortUrl(FullUrl targetUrl, String shortUrl) {

        final ShortUrl newShortUrl = ShortUrl
            .builder()
            .id(ShortUrlFactory.SHORT_URL_ID)
            .fullUrl(targetUrl)
            .shortUrl(shortUrl)
            .enabled(true)
            .build();

        FullUrlFactory.safeAddShortUrl(targetUrl, newShortUrl);
    }

    static void safeAddShortUrl(FullUrl targetUrl, ShortUrl newShortUrl) {

        Set<ShortUrl> shortUrls = new HashSet<>(targetUrl.shortUrls());

        shortUrls.add(newShortUrl);

        ReflectionTestUtils.setField(targetUrl, "shortUrls", shortUrls);
    }
}
