package io.github.danilkuznetsov.urlshortener.strategies;

/**
 * @author Danil Kuznetsov
 */
public abstract class GeneratorShortUrl {
    public  String encodeLongUrl(String longUrl){
        return makeHash(longUrl);
    };

    protected abstract String makeHash(String longUrl);
}
