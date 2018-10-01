package pl.napiwo.standalone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @author
 * Karol Meksuła
 * 01-10-2018
 * */

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void userJson(Principal principal) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;

        Map<?,?> credentials = (Map<?, ?>) authentication.getUserAuthentication().getDetails();

        String name = (String) credentials.get("name");
        String userId = (String) credentials.get("id");
    }

}
