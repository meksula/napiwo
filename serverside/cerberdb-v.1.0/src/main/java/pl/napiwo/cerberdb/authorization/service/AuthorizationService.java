package pl.napiwo.cerberdb.authorization.service;

import pl.napiwo.cerberdb.authorization.dto.AuthorizationResponse;
import pl.napiwo.cerberdb.dto.LoginObject;

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

public interface AuthorizationService {
    AuthorizationResponse authorize(final LoginObject loginObject);
}
