package pl.napiwo.scribe.controller;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.napiwo.scribe.dto.AuthorizationResponse;
import pl.napiwo.scribe.dto.LoginObject;
import pl.napiwo.scribe.url.ApiUrls;

/**
 * @author
 * Karol Meksuła
 * 05-10-2018
 * */

@Controller
@RequestMapping("/scribe")
public class MainController {
    private RestTemplate restTemplate;

    public MainController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/login")
    public String scribeLogin(LoginObject loginObject, final BindingResult bindingResult, final ModelMap model) {
        model.addAttribute(loginObject);
        return "login";
    }

    @PostMapping("/login/request")
    public String scribeLogin(final LoginObject loginObject) {
        HttpEntity<LoginObject> entity = new HttpEntity<>(loginObject);
        final String URL = ApiUrls.CERBER_USER_AUTH.getUrl();
        AuthorizationResponse responseEntity = restTemplate.postForEntity(URL, entity, AuthorizationResponse.class).getBody();

        if (responseEntity != null && responseEntity.isAuthorized()) {
            return "redirect:http://localhost:8050";
        }

        return "login_failed";
    }

}
