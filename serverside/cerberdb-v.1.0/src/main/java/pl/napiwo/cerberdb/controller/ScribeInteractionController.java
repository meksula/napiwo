package pl.napiwo.cerberdb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.napiwo.cerberdb.access.DataAccess;
import pl.napiwo.cerberdb.authorization.dto.AuthorizationResponse;
import pl.napiwo.cerberdb.authorization.service.AuthorizationService;
import pl.napiwo.cerberdb.dto.LoginObject;
import pl.napiwo.cerberdb.dto.UserAccessEntity;
import pl.napiwo.cerberdb.dto.UserAccessKey;

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

@Slf4j
@RestController
@RequestMapping("/api/v1/cerber")
public class ScribeInteractionController {
    private DataAccess dataAccess;
    private AuthorizationService authorizationService;

    public ScribeInteractionController(DataAccess dataAccess, AuthorizationService authorizationService) {
        this.dataAccess = dataAccess;
        this.authorizationService = authorizationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String saveFreshAccessToken(@RequestBody UserAccessKey userAccessKey) {
        UserAccessEntity entity = dataAccess.encryptAndSave(userAccessKey);
        return "New encryptedAccessToken established for userProfileId : " + entity.getUserProfileId();
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public AuthorizationResponse authorizationFromScribe(@RequestBody LoginObject loginObject) {
        return authorizationService.authorize(loginObject);
    }

}
