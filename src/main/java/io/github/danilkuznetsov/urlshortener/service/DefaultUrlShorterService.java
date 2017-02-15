package io.github.danilkuznetsov.urlshortener.service;

import io.github.danilkuznetsov.urlshortener.strategies.GeneratorShortUrl;
import io.github.danilkuznetsov.urlshortener.strategies.GeneratorMD5ShortUrl;

import java.util.HashMap;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public class DefaultUrlShorterService implements UrlShorterService {


    private final GeneratorShortUrl generator = new GeneratorMD5ShortUrl();
    private HashMap<String, String> urls = new HashMap<>();


    @Override
    public String createNewShortUrl(String longUrl) {
        String shortUrl = generator.encodeLongUrl(longUrl);
        urls.put(shortUrl, longUrl);
        return shortUrl;
    }

    @Override
    public String findLongUrlByShortUrl(String shortUrl) {
        return urls.get(shortUrl);
    }

    @Override
    public void updateLongUrlByShortUrl(String shortUrl, String newLongUrl) {
        urls.put(shortUrl, newLongUrl);
    }
}
