package io.github.danilkuznetsov.cleverlinks.factories;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public interface FullUrlFactory {

    Long FIRST_URL_ID = 1L;
    String FIRST_URL = "http://google.com";

    static FullUrl fullUrl() {

        final FullUrl url = FullUrl
            .builder()
            .id(FullUrlFactory.FIRST_URL_ID)
            .url(FullUrlFactory.FIRST_URL)
            .build();

        url.addShortUrl(ShortUrlFactory.SHORT_URL);

        return url;
    }

    static void safeSetId(FullUrl targetUrl, Long id) {
        ReflectionTestUtils.setField(targetUrl, "id", id);
    }
}
