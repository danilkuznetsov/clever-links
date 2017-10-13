package io.github.danilkuznetsov.urlshortener.service;

import io.github.danilkuznetsov.urlshortener.strategies.GeneratorFactory;
import io.github.danilkuznetsov.urlshortener.strategies.GeneratorShortUrl;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public class DefaultUrlShorterService implements UrlShorterService {

    private GeneratorFactory generatorFactory;
    private HashMap<String, String> urls = new HashMap<>();

    @Inject
    public DefaultUrlShorterService(GeneratorFactory generatorFactory) {
        this.generatorFactory = generatorFactory;
    }

    @Override
    public String createNewShortUrl(String longUrl) {
        GeneratorShortUrl generator = generatorFactory.createGenerator("");
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

    @Override
    public List<String> findAllUrl() {
        return new ArrayList<>(urls.values());
    }
}
