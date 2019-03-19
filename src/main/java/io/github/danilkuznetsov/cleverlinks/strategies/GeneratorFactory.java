package io.github.danilkuznetsov.cleverlinks.strategies;

/**
 * @author Danil Kuznetsov
 */
public interface GeneratorFactory {
    GeneratorShortUrl createGenerator(String type);
}
