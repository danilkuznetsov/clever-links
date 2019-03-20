package io.github.danilkuznetsov.cleverlinks.config;

import io.github.danilkuznetsov.cleverlinks.repositories.FullUrlRepository;
import io.github.danilkuznetsov.cleverlinks.services.UrlService;
import io.github.danilkuznetsov.cleverlinks.services.UrlServiceImpl;
import io.github.danilkuznetsov.cleverlinks.services.strategies.DefaultGeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Configuration
public class UrlGenerationConfiguration {

    private final FullUrlRepository urlRepository;

    @Autowired
    public UrlGenerationConfiguration(final FullUrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Bean
    public UrlService urlShortenerService() {
        return new UrlServiceImpl(this.urlRepository, this.generatorFactory());
    }

    @Bean
    public GeneratorFactory generatorFactory() {
        return new DefaultGeneratorFactory();
    }
}
