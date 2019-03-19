package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import java.util.List;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public interface UrlShorterService {
    String createNewShortUrl(String fullUrl);

    String findLongUrlByShortUrl(String shortUrl);

    void updateLongUrlByShortUrl(String shortUrl, String expectedAfterUpdateLongUrl);

    List<FullUrl> loadUrls();
}
