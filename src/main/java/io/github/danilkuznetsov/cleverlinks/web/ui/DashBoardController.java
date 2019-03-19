package io.github.danilkuznetsov.cleverlinks.web.ui;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.services.UrlShorterService;
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
@RequestMapping("/dashboard")
public class DashBoardController {

    private final UrlShorterService urlService;

    @Autowired
    public DashBoardController(UrlShorterService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public String dashboardHome(final Model model) {
        List<FullUrl> urls = this.urlService.loadUrls();
        model.addAttribute("urls", urls);
        return "dashboard/dashboard";
    }

}
