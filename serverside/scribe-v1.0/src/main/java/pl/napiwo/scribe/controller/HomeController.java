package pl.napiwo.scribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author
 * Karol Meksuła
 * 05-10-2018
 * */

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/napiwo/login")
    public String scribeLogin() {
        return "login";
    }

    @GetMapping("/napiwo/register")
    public String scribeRegister() {
        return "register";
    }

}
