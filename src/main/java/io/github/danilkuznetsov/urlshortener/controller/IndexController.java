package io.github.danilkuznetsov.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by danil.kuznetsov on 31/01/17.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Hello world! I'm url shortener service";
    }


}
