package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.repositories.FullUrlRepository;
import io.github.danilkuznetsov.cleverlinks.services.exceptions.FullUrlAlreadyExistException;
import io.github.danilkuznetsov.cleverlinks.services.exceptions.FullUrlNotFoundException;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorShortUrl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
@Service
@Transactional(readOnly = true)
public class UrlServiceImpl implements UrlService {

    private final FullUrlRepository urlRepository;
    private final UrlCache urlCache;
    private final GeneratorFactory generatorFactory;

    @Autowired
    public UrlServiceImpl(
        final FullUrlRepository urlRepository,
        final UrlCache urlCache,
        final GeneratorFactory urlGeneratorFactory
    ) {
        this.urlRepository = urlRepository;
        this.urlCache = urlCache;
        this.generatorFactory = urlGeneratorFactory;
    }

    @Override
    @Transactional
    public FullUrlDescription createUrl(String newUrl) {

        if (this.urlRepository.existsByUrl(newUrl)) {
            throw new FullUrlAlreadyExistException();
        }

        GeneratorShortUrl generator = this.generatorFactory.createGenerator("");

        FullUrl url = FullUrl.builder()
            .url(newUrl)
            .build();

        url.addShortUrl(generator.encodeLongUrl(newUrl));

        url = this.urlRepository.save(url);

        this.urlCache.put(url);

        return FullUrlDescription.of(url);
    }

    @Override
    public List<FullUrlDescription> loadUrls() {
        return this.urlRepository.findAll()
            .stream()
            .map(FullUrlDescription::of)
            .collect(Collectors.toList());
    }

    @Override
    public FullUrlDetails loadDetails(final Long urlId) {
        return this.urlRepository.findDetailsById(urlId)
            .map(FullUrlDetails::of)
            .orElseThrow(FullUrlNotFoundException::new);
    }

    @Override
    @Transactional
    public FullUrlDescription addCustomShortUrl(final Long urlId, final String newCustomShortUrl) {

        final FullUrl url = this.urlRepository.findDetailsById(urlId)
            .orElseThrow(FullUrlNotFoundException::new);

        url.addShortUrl(newCustomShortUrl);

        this.urlCache.put(url);

        return FullUrlDescription.of(url);
    }
}
