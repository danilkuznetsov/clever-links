package io.github.danilkuznetsov.urlshortener.strategies;

/**
 * @author Danil Kuznetsov
 */
public interface GeneratorFactory {
    GeneratorShortUrl createGenerator(String type);
}
