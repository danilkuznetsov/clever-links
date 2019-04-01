package io.github.danilkuznetsov.cleverlinks.factories;

import io.github.danilkuznetsov.cleverlinks.domain.ShortUrl;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public interface ShortUrlFactory {

    Long SHORT_URL_ID = 1L;
    String SHORT_URL = "http://su.io/short";

    Long CUSTOM_SHORT_URL_ID = 2L;
    String CUSTOM_SHORT_URL = "http://su.io/custom-short-url";

    static ShortUrl shortUrl() {
        return ShortUrl
            .builder()
            .id(ShortUrlFactory.SHORT_URL_ID)
            .shortUrl(ShortUrlFactory.SHORT_URL)
            .enabled(true)
            .build();
    }

    static ShortUrl customShortUrl() {
        return ShortUrl
            .builder()
            .id(ShortUrlFactory.CUSTOM_SHORT_URL_ID)
            .shortUrl(ShortUrlFactory.CUSTOM_SHORT_URL)
            .enabled(true)
            .build();
    }
}
