package io.github.danilkuznetsov.urlshortener.strategies;

/**
 * @author Danil Kuznetsov
 */
public interface GeneratorShortUrl {
    String encodeLongUrl(String longUrl);
}
