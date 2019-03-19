package io.github.danilkuznetsov.cleverlinks.web.api;

import io.github.danilkuznetsov.cleverlinks.services.UrlService;
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
public class UrlShortResource {

    private final UrlService urlService;

    @Autowired
    public UrlShortResource(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping("/short/new")
    public String createNewShortUrl(@RequestParam("url") String longUrl) {
        return urlService.createNewShortUrl(longUrl);
    }

    @RequestMapping("/short/{id}")
    public String getLongUrl(@PathVariable("id") String shortUrl) {
        return urlService.findLongUrlByShortUrl(shortUrl);
    }
}
