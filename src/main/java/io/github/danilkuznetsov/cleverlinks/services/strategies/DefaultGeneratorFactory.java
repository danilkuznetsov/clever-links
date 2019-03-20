package io.github.danilkuznetsov.cleverlinks.services.strategies;

import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorMD5ShortUrl;
import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorShortUrl;

/**
 * @author Danil Kuznetsov
 */
public class DefaultGeneratorFactory implements GeneratorFactory {

    @Override
    public GeneratorShortUrl createGenerator(String type) {
        GeneratorShortUrl generatorShortUrl;
        switch (type) {
            default:
                generatorShortUrl = new GeneratorMD5ShortUrl();
                break;
        }
        return generatorShortUrl;
    }

}
