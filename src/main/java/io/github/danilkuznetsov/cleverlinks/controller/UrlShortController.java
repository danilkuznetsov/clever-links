package io.github.danilkuznetsov.cleverlinks.controller;

import io.github.danilkuznetsov.cleverlinks.service.UrlShorterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by danil.kuznetsov on 31/01/17.
 */
@RestController
@RequestMapping("/api/urls")
public class UrlShortController {

    private final UrlShorterService urlShorterService;

    @Autowired
    public UrlShortController(UrlShorterService urlShorterService) {
        this.urlShorterService = urlShorterService;
    }

    @RequestMapping("/short/new")
    public String createNewShortUrl(@RequestParam("url") String longUrl) {
        return urlShorterService.createNewShortUrl(longUrl);
    }

    @RequestMapping("/short/{id}")
    public String getLongUrl(@PathVariable("id") String shortUrl) {
        return urlShorterService.findLongUrlByShortUrl(shortUrl);
    }
}
