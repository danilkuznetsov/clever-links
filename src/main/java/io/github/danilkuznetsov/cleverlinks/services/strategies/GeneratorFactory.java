package io.github.danilkuznetsov.cleverlinks.services.strategies;

/**
 * @author Danil Kuznetsov
 */
public interface GeneratorFactory {
    GeneratorShortUrl createGenerator(String type);
}
