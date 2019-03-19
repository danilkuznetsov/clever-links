package io.github.danilkuznetsov.cleverlinks.web;

import io.github.danilkuznetsov.cleverlinks.services.UrlShorterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Controller
public class RedirectHandler {

    private final UrlShorterService urlService;

    @Autowired
    public RedirectHandler(final UrlShorterService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping("/{id}")
    public String redirectToLongUrl(@PathVariable("id") String shortUrl) {
        String longUrl = this.urlService.findLongUrlByShortUrl(shortUrl);
        if (longUrl != null && !longUrl.isEmpty()) {
            return "redirect:" + longUrl;
        }
        return "redirect:/";
    }
}
