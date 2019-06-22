package io.github.danilkuznetsov.cleverlinks.services.strategies;

import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorMD5ShortUrl;
import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorShortUrl;

/**
 * @author Danil Kuznetsov
 */
public class GeneratorFactoryImpl implements GeneratorFactory {

    @Override
    public GeneratorShortUrl createGenerator(final String type) {
        final GeneratorShortUrl generatorShortUrl;
        switch (type) {
            default:
                generatorShortUrl = new GeneratorMD5ShortUrl();
                break;
        }
        return generatorShortUrl;
    }

    @Override
    public GeneratorShortUrl defaultGenerator() {
        return this.createGenerator("");
    }
}
