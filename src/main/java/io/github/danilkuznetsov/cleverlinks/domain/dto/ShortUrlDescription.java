package io.github.danilkuznetsov.cleverlinks.domain.dto;

import io.github.danilkuznetsov.cleverlinks.domain.ShortUrl;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
@ToString
public class ShortUrlDescription {

    private final Long id;

    private final String url;

    private final boolean enabled;

    public static ShortUrlDescription of(final ShortUrl shortUrl) {
        return new ShortUrlDescription(
            shortUrl.getId(),
            shortUrl.getUrl(),
            shortUrl.getEnabled()
        );
    }
}
