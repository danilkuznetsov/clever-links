package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import java.util.List;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public interface UrlService {

    String createShortUrl(String fullUrl);

    String findLongUrlByShortUrl(String shortUrl);

    void updateLongUrlByShortUrl(String shortUrl, String expectedAfterUpdateLongUrl);

    List<FullUrlDetails> loadUrls();
}
