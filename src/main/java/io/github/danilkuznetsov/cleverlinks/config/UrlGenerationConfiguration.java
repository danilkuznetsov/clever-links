package io.github.danilkuznetsov.cleverlinks.config;

import io.github.danilkuznetsov.cleverlinks.services.DefaultUrlShorterService;
import io.github.danilkuznetsov.cleverlinks.services.UrlShorterService;
import io.github.danilkuznetsov.cleverlinks.services.strategies.DefaultGeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Configuration
public class UrlGenerationConfiguration {

    @Bean
    public UrlShorterService urlShortenerService() {
        return new DefaultUrlShorterService(this.generatorFactory());
    }

    @Bean
    public GeneratorFactory generatorFactory() {
        return new DefaultGeneratorFactory();
    }
}
