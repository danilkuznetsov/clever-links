package io.github.danilkuznetsov.cleverlinks.domain.dto;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
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
@EqualsAndHashCode(exclude = "count")
@ToString
public class FullUrlDescription {

    private final Long id;

    private final String url;

    @Getter(AccessLevel.NONE)
    private final int count;

    public static FullUrlDescription of(final FullUrl url) {
        return new FullUrlDescription(url.getId(), url.getUrl(), url.shortUrls().size());
    }

    public int countShortUrls() {
        return this.count;
    }
}
