package pl.napiwo.doorman.services.implementation;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import pl.napiwo.doorman.dto.UserProfile;
import pl.napiwo.doorman.repository.UserProfileRepository;
import pl.napiwo.doorman.services.UserExistService;

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
    private final String INVALID_JSON = "oAuth 2.0 provider did not send us correct object.";

    public UserExistServiceDefault(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public boolean isUserExist(OAuth2Authentication auth2Authentication) {
        if (auth2Authentication == null) {
            throw new IllegalArgumentException(INVALID_JSON);
        }

        return userProfileRepository.findBySocialServiceId(assignSocialServiceId(auth2Authentication)).isPresent();
    }

    @Override
    public boolean isUserExist(String socialServiceId, String socialUserName) {
        if(socialUserName == null) {
            throw new IllegalArgumentException(INVALID_JSON);
        }

        return userProfileRepository.findBySocialServiceIdAndAndSocialUserName(socialServiceId, socialUserName).isPresent();
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