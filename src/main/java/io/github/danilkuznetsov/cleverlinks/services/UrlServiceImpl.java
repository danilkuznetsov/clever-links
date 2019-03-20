package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.repositories.FullUrlRepository;
import io.github.danilkuznetsov.cleverlinks.services.exceptions.FullUrlAlreadyExist;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorShortUrl;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public class UrlServiceImpl implements UrlService {

    private final FullUrlRepository urlRepository;
    private final GeneratorFactory generatorFactory;

    private final HashMap<String, String> urls = new HashMap<>();

    public UrlServiceImpl(
        final FullUrlRepository urlRepository,
        final GeneratorFactory urlGeneratorFactory
    ) {
        this.urlRepository = urlRepository;
        this.generatorFactory = urlGeneratorFactory;
    }

    @Override
    public String createShortUrl(String fullUrl) {

        if (this.urlRepository.existsByUrl(fullUrl)) {
            throw new FullUrlAlreadyExist();
        }

        GeneratorShortUrl generator = this.generatorFactory.createGenerator("");
        String shortUrl = generator.encodeLongUrl(fullUrl);

        this.urls.put(shortUrl, fullUrl);
        return shortUrl;
    }

    @Override
    public String findLongUrlByShortUrl(String shortUrl) {
        return this.urls.get(shortUrl);
    }

    @Override
    public void updateLongUrlByShortUrl(String shortUrl, String newLongUrl) {
        this.urls.put(shortUrl, newLongUrl);
    }

    @Override
    public List<FullUrlDetails> loadUrls() {
        return this.urlRepository.findAll()
            .stream()
            .map(FullUrlDetails::of)
            .collect(Collectors.toList());
    }
}
