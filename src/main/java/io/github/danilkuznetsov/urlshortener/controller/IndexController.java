package io.github.danilkuznetsov.urlshortener.controller;

import io.github.danilkuznetsov.urlshortener.service.UrlShorterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by danil.kuznetsov on 31/01/17.
 */

@Controller
public class IndexController {

    @Inject
    private UrlShorterService urlService;

    @RequestMapping("/{id}")
    public String redirectToLongUrl(@PathVariable("id") String shortUrl) {
        String longUrl = urlService.findLongUrlByShortUrl(shortUrl);
        if ((longUrl != null) && (!longUrl.isEmpty())) {
            return "redirect:" + longUrl;
        }
        return "redirect:/home";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "/index";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
