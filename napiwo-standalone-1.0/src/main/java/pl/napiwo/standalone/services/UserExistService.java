package pl.napiwo.standalone.services;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import pl.napiwo.standalone.dto.UserProfile;

/**
 * @author
 * Karol Meksuła
 * 03-10-2018
 * */

public interface UserExistService {
    boolean isUserExist(OAuth2Authentication auth2Authentication);

    UserProfile fetchUserProfile(OAuth2Authentication auth2Authentication);
}
