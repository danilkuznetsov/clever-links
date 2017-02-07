package io.github.danilkuznetsov.urlshortener.controller;

import io.github.danilkuznetsov.urlshortener.service.UrlShorterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by danil.kuznetsov on 31/01/17.
 */

@Controller
public class IndexController {

    @Inject
    private UrlShorterService urlService;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello world! I'm url shortener service";
    }

    @RequestMapping("/{id}")
    public String redirectToLongUrl(@PathVariable("id") String shortUrl) {
        String longUrl = urlService.findLongUrlByShortUrl(shortUrl);
        if ((longUrl != null) && (!longUrl.isEmpty())) {
            return "redirect:" + longUrl;
        }
        return "redirect:/";
    }


}
