package io.github.danilkuznetsov.service;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public interface UrlShorterService {
    String createNewShortUrl(String fullUrl);

    String findLongUrlByShortUrl(String shortUrl);

}
