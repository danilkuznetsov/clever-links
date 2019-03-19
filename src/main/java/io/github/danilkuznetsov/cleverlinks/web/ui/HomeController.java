package io.github.danilkuznetsov.cleverlinks.web.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by danil.kuznetsov on 31/01/17.
 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
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
