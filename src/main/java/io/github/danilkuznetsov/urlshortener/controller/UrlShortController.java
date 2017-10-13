package io.github.danilkuznetsov.urlshortener.controller;

import io.github.danilkuznetsov.urlshortener.service.UrlShorterService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by danil.kuznetsov on 31/01/17.
 */
@RestController
@RequestMapping("/api/urls")
public class UrlShortController {

    private UrlShorterService urlShorterService;

    @Inject
    public UrlShortController(UrlShorterService urlShorterService) {
        this.urlShorterService = urlShorterService;
    }

    @RequestMapping("/short/new")
    public String createNewShortUrl(@RequestParam("url") String longUrl) {
        return urlShorterService.createNewShortUrl(longUrl);
    }

    @RequestMapping("/short/{id}")
    public String getLongUrl(@PathVariable("id") String shortUrl){
        return urlShorterService.findLongUrlByShortUrl(shortUrl);
    };
}
