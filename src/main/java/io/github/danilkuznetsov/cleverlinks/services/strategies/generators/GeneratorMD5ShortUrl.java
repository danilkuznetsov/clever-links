package io.github.danilkuznetsov.cleverlinks.services.strategies.generators;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneratorMD5ShortUrl implements GeneratorShortUrl {
    @Override
    public String encodeLongUrl(String longUrl) {
        MessageDigest digest = null;
        // TODO handle exceptions
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(longUrl.getBytes("UTF-8"), 0, longUrl.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new BigInteger(1, digest.digest()).toString(16);
    }
}
