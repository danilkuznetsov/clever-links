package io.github.danilkuznetsov.cleverlinks.web.ui;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.services.UrlService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<FullUrlDescription> urls = this.urlService.loadUrls();
        model.addAttribute("urls", urls);
        return "dashboard/dashboard";
    }

    @GetMapping("/urls/{urlId}")
    public String displayFullUrlDetailsPage(
        @PathVariable("urlId") final Long urlId,
        final Model model
    ) {
        model.addAttribute("fullUrlDetails", this.urlService.loadDetails(urlId));
        return "dashboard/fullUrlDetails";
    }
}
