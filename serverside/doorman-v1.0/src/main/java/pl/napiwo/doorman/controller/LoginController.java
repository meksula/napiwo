package pl.napiwo.doorman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.napiwo.standalone.services.UserExistService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author
 * Karol Meksuła
 * 05-10-2018
 * */

@Controller
@RequestMapping("/napiwo/login")
public class LoginController {
    private UserExistService userExistService;

    public LoginController(UserExistService userExistService) {
        this.userExistService = userExistService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String loginMethodChoose() {
        return "login";
    }

    @GetMapping("/facebook")
    @ResponseStatus(HttpStatus.OK)
    public String loginByFacebook(Principal principal, HttpServletRequest request, Model model) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        boolean isUserExist = userExistService.isUserExist(authentication);

        if (!isUserExist) {
            return "user_not_exist_page";
        }

        model.addAttribute("userProfile", userExistService.fetchUserProfile(authentication));
        return "dashboard";
    }

    @GetMapping("/credentials")
    @ResponseStatus(HttpStatus.OK)
    public String loginByTypeCredentials() {
        return "dashboard";
    }

}
