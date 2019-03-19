package io.github.danilkuznetsov.cleverlinks.web.ui;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.services.UrlService;
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

    private final UrlService urlService;

    @Autowired
    public DashBoardController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public String dashboardHome(final Model model) {
        List<FullUrlDetails> urls = this.urlService.loadUrls();
        model.addAttribute("urls", urls);
        return "dashboard/dashboard";
    }

}
