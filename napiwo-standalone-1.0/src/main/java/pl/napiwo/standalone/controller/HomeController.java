package pl.napiwo.standalone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author
 * Karol Meksuła
 * 30-09-2018
 * */

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "index";
    }

}
