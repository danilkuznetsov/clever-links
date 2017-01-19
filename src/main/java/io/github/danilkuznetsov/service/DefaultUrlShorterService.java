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

    public String createNewShortUrl(String fullUrl) {
        String shortUrl = generateShortUrl(fullUrl);
        urls.put(shortUrl, fullUrl);
        return shortUrl;
    }

    private String generateShortUrl(String fullUrl) {
        MessageDigest digest = null;

        // TO-DO handle exceptions
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(fullUrl.getBytes("UTF-8"), 0, fullUrl.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new BigInteger(1, digest.digest()).toString(16);
    }

    public String findLongUrlByShortUrl(String shortUrl) {
        return urls.get(shortUrl);
    }
}
