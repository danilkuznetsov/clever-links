package io.github.danilkuznetsov.cleverlinks.services.strategies;

import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorShortUrl;

/**
 * @author Danil Kuznetsov
 */
public interface GeneratorFactory {
    GeneratorShortUrl createGenerator(String type);
}
