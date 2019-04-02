package io.github.danilkuznetsov.cleverlinks.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdatedShortUrl {

    private final Long id;

    private final String newUrl;
}
