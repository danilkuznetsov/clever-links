package io.github.danilkuznetsov.cleverlinks.factories.dto;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public interface FullUrlDescriptionFactory {

    static FullUrlDescription urlDescription() {
        return FullUrlDescription.of(FullUrlFactory.fullUrl());
    }
}
