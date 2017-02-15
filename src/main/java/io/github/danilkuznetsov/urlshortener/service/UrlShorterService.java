package io.github.danilkuznetsov.urlshortener.service;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public interface UrlShorterService {
    String createNewShortUrl(String fullUrl);
    String findLongUrlByShortUrl(String shortUrl);
    void updateLongUrlByShortUrl(String shortUrl, String expectedAfterUpdateLongUrl);
}