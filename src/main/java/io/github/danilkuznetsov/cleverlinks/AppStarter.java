package io.github.danilkuznetsov.cleverlinks;

import io.github.danilkuznetsov.cleverlinks.service.DefaultUrlShorterService;
import io.github.danilkuznetsov.cleverlinks.service.UrlShorterService;
import io.github.danilkuznetsov.cleverlinks.strategies.DefaultGeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.strategies.GeneratorFactory;
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
