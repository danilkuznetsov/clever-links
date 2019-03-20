package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import java.util.Collection;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public interface UrlCache {

    String resolveUrl(String shortUrl);

    void put(FullUrl url);

    void putAll(Collection<FullUrl> urls);
}
