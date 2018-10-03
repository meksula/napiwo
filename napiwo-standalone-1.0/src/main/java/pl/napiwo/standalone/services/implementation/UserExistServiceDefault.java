package pl.napiwo.standalone.services.implementation;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import pl.napiwo.standalone.dto.UserProfile;
import pl.napiwo.standalone.repository.UserProfileRepository;
import pl.napiwo.standalone.services.UserExistService;

import java.util.Map;

/**
 * @author
 * Karol Meksuła
 * 03-10-2018
 * */

@Service
public class UserExistServiceDefault implements UserExistService {
    private UserProfileRepository userProfileRepository;
    private final String EXCEPTION_MESSAGE = "Something went wrong. Cannot fetch UserProfile.class entity.";

    public UserExistServiceDefault(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public boolean isUserExist(OAuth2Authentication auth2Authentication) {
        if (auth2Authentication == null) {
            throw new IllegalArgumentException("oAuth 2.0 provider did not send us correct object.");
        }

        return userProfileRepository.findBySocialServiceId(assignSocialServiceId(auth2Authentication)).isPresent();
    }

    @Override
    public UserProfile fetchUserProfile(OAuth2Authentication auth2Authentication) {
        String socialServiceId = assignSocialServiceId(auth2Authentication);

        return userProfileRepository.findBySocialServiceId(socialServiceId)
                .orElseThrow(() -> new RuntimeException(EXCEPTION_MESSAGE));
    }

    private String assignSocialServiceId(OAuth2Authentication authentication) {
        try {
            Map<?,?> credentials = (Map<?, ?>) authentication.getUserAuthentication().getDetails();
            return  (String) credentials.get("id");
        } catch (RuntimeException exception) {
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
    }

}
