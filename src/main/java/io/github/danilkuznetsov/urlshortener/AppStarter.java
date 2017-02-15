package io.github.danilkuznetsov.urlshortener;

import io.github.danilkuznetsov.urlshortener.service.DefaultUrlShorterService;
import io.github.danilkuznetsov.urlshortener.service.UrlShorterService;
import io.github.danilkuznetsov.urlshortener.strategies.DefaultGeneratorFactory;
import io.github.danilkuznetsov.urlshortener.strategies.GeneratorFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }

    @Bean
    public UrlShorterService createUrlShortenerService() {
        return new DefaultUrlShorterService(createGeneratorFactory());
    }

    @Bean
    public GeneratorFactory createGeneratorFactory() {
        return new DefaultGeneratorFactory();
    }
}
