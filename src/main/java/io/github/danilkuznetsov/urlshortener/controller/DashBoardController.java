package io.github.danilkuznetsov.urlshortener.controller;

import io.github.danilkuznetsov.urlshortener.service.UrlShorterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Controller
@RequestMapping("/dashboard/")
public class DashBoardController {

    private UrlShorterService urlService;

    @Inject
    public DashBoardController(UrlShorterService urlService) {this.urlService = urlService;}

    @GetMapping("/home")
    public String dashboardHome(Model model) {
        List<String> urls = urlService.findAllUrl();
        model.addAttribute("urls", urls);
        return "/dashboard/home";
    }

}
