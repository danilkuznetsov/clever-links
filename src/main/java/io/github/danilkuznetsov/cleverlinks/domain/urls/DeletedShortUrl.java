package io.github.danilkuznetsov.cleverlinks.domain.urls;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class DeletedShortUrl {
    private final Long fullUrlId;
    private final Long shortUrlId;

    public static DeletedShortUrl of(final Long fullUrlId, final Long shortUrlId) {
        return new DeletedShortUrl(fullUrlId, shortUrlId);
    }
}
