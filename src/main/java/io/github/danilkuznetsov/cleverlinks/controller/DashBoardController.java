package io.github.danilkuznetsov.cleverlinks.controller;

import io.github.danilkuznetsov.cleverlinks.service.UrlShorterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Controller
@RequestMapping("/dashboard/")
public class DashBoardController {

    private final UrlShorterService urlService;

    @Autowired
    public DashBoardController(UrlShorterService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/home")
    public String dashboardHome(Model model) {
        List<String> urls = urlService.findAllUrl();
        model.addAttribute("urls", urls);
        return "/dashboard/home";
    }

}
