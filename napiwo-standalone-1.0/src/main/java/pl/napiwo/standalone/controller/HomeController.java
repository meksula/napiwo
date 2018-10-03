package pl.napiwo.standalone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.napiwo.standalone.services.UserExistService;

import java.security.Principal;

/**
 * @author
 * Karol Meksuła
 * 30-09-2018
 * */

@Controller
@RequestMapping("/")
public class HomeController {
    private UserExistService userExistService;

    public HomeController(UserExistService userExistService) {
        this.userExistService = userExistService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "index";
    }

    @GetMapping("/dashboard")
    @ResponseStatus(HttpStatus.OK)
    public String dashboard(Principal principal, Model model) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        boolean isUserExist = userExistService.isUserExist(authentication);

        if (!isUserExist) {
            return "user_not_exist_page";
        }

        model.addAttribute("userProfile", userExistService.fetchUserProfile(authentication));
        return "dashboard";
    }

}
