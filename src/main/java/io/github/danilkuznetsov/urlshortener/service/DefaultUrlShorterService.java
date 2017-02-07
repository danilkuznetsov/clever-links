package io.github.danilkuznetsov.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public class DefaultUrlShorterService implements UrlShorterService {


    private HashMap<String, String> urls = new HashMap<>();


    @Override
    public String createNewShortUrl(String longUrl) {
        String shortUrl = generateShortUrl(longUrl);
        urls.put(shortUrl, longUrl);
        return shortUrl;
    }

    private String generateShortUrl(String longUrl) {
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

    @Override
    public String findLongUrlByShortUrl(String shortUrl) {
        return urls.get(shortUrl);
    }

    @Override
    public void updateLongUrlByShortUrl(String shortUrl, String newLongUrl) {
        urls.put(shortUrl,newLongUrl);
    }
}
