package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorShortUrl;
import java.util.Collections;
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
    public List<FullUrl> loadUrls() {
        FullUrl fakeUrl = FullUrl.builder().url("fake").build();
        return Collections.singletonList(fakeUrl);
    }
}
