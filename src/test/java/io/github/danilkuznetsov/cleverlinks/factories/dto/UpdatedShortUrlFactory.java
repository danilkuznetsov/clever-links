package io.github.danilkuznetsov.cleverlinks.factories.dto;

import io.github.danilkuznetsov.cleverlinks.domain.dto.UpdatedShortUrl;
import io.github.danilkuznetsov.cleverlinks.factories.ShortUrlFactory;

public interface UpdatedShortUrlFactory {

    static UpdatedShortUrl updatedUrl() {
        return new UpdatedShortUrl(ShortUrlFactory.SHORT_URL_ID, ShortUrlFactory.CUSTOM_SHORT_URL);
    }
}
