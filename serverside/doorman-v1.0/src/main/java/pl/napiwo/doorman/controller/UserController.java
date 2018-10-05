package pl.napiwo.doorman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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

    }

}
