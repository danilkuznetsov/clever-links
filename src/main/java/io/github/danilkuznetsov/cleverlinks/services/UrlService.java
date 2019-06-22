package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.domain.dto.UpdatedShortUrl;
import io.github.danilkuznetsov.cleverlinks.domain.urls.DeletedShortUrl;
import java.util.List;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public interface UrlService {

    FullUrlDescription createUrl(String url);

    List<FullUrlDescription> loadUrls();

    FullUrlDetails loadDetails(Long urlId);

    FullUrlDescription addCustomShortUrl(Long urlId, String newCustomShortUrl);

    FullUrlDescription updateCustomShortUrl(Long urlId, UpdatedShortUrl updatedUrl);

    DeletedShortUrl deleteShortUrl(Long fullUrlId, Long shortUrlId);
}
