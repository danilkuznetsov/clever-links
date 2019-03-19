package io.github.danilkuznetsov.cleverlinks.service;

import io.github.danilkuznetsov.cleverlinks.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.strategies.GeneratorShortUrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public class DefaultUrlShorterService implements UrlShorterService {

    private GeneratorFactory generatorFactory;
    private HashMap<String, String> urls = new HashMap<>();

    public DefaultUrlShorterService(final GeneratorFactory urlGeneratorFactory) {
        this.generatorFactory = urlGeneratorFactory;
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
