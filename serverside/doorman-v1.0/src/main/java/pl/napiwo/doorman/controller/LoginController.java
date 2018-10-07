package pl.napiwo.doorman.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;

/**
 * @author
 * Karol Meksuła
 * 05-10-2018
 * */

@Controller
@RequestMapping("/napiwo/login")
@Slf4j
public class LoginController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String loginMethodChoose() {
        return "login";
    }

    @GetMapping("/facebook")
    @ResponseStatus(HttpStatus.OK)
    public String loginByFacebook(Principal principal) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;

        if (authentication.isAuthenticated()) {
            return "redirect:http://localhost:8050";
        }

        return "user_not_exist_page";
    }

}
