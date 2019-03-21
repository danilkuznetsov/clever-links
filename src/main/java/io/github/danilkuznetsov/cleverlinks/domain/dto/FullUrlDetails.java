package io.github.danilkuznetsov.cleverlinks.domain.dto;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import java.util.List;
import java.util.stream.Collectors;
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
@ToString
@EqualsAndHashCode(exclude = "shortUrls")
public class FullUrlDetails {

    private final FullUrlDescription urlDescription;

    private final List<ShortUrlDescription> shortUrls;

    public static FullUrlDetails of(final FullUrl fullUrl) {

        List<ShortUrlDescription> shortUrls = fullUrl.shortUrls()
            .stream()
            .map(ShortUrlDescription::of)
            .collect(Collectors.toList());

        return new FullUrlDetails(
            FullUrlDescription.of(fullUrl),
            shortUrls
        );
    }
}
