package io.github.danilkuznetsov.urlshortener.strategies;

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
