package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.repositories.FullUrlRepository;
import io.github.danilkuznetsov.cleverlinks.services.exceptions.FullUrlAlreadyExist;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorShortUrl;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public class UrlServiceImpl implements UrlService {

    private final FullUrlRepository urlRepository;
    private final GeneratorFactory generatorFactory;


    public UrlServiceImpl(
        final FullUrlRepository urlRepository,
        final GeneratorFactory urlGeneratorFactory
    ) {
        this.urlRepository = urlRepository;
        this.generatorFactory = urlGeneratorFactory;
    }

    @Override
    public FullUrlDescription createUrl(String url) {

        if (this.urlRepository.existsByUrl(url)) {
            throw new FullUrlAlreadyExist();
        }

        GeneratorShortUrl generator = this.generatorFactory.createGenerator("");

        FullUrl fullUrl = FullUrl.builder()
            .url(url)
            .build();

        fullUrl.addShortUrl(generator.encodeLongUrl(url));

        return FullUrlDescription.of(fullUrl);
    }

    @Override
    public List<FullUrlDescription> loadUrls() {
        return this.urlRepository.findAll()
            .stream()
            .map(FullUrlDescription::of)
            .collect(Collectors.toList());
    }
}
