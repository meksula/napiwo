package pl.napiwo.cerberdb.authorization.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.napiwo.cerberdb.authorization.dto.AuthorizationResponse;
import pl.napiwo.cerberdb.dto.LoginObject;
import pl.napiwo.cerberdb.dto.UserCredentials;
import pl.napiwo.cerberdb.exception.CerberdbDataNotFound;
import pl.napiwo.cerberdb.repository.UserCredentialsRepository;

import java.time.LocalDateTime;

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

@Service
public class DefaultAuthorizationService implements AuthorizationService {
    private UserCredentialsRepository userCredentialsRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public DefaultAuthorizationService(UserCredentialsRepository userCredentialsRepository,
                                       BCryptPasswordEncoder passwordEncoder) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthorizationResponse authorize(LoginObject loginObject) {
        String username = loginObject.getUsername();
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(loginObject.getUsername())
                .orElseThrow(() -> new CerberdbDataNotFound("UserCredentials.class", username));

        boolean result = passwordEncoder.matches(loginObject.getDecryptedPassword(), userCredentials.getEncryptedPassword());
        return buildAuthResponse(userCredentials, result);
    }

    private AuthorizationResponse buildAuthResponse(UserCredentials userCredentials, boolean result) {
        AuthorizationResponse response = new AuthorizationResponse();
        response.setUsername(userCredentials.getUsername());
        response.setAuthType(userCredentials.getAuthorizationType().name());
        response.setAuthDate(LocalDateTime.now().toString());
        response.setAuthorities(userCredentials.getAuthorities());
        response.setAuthorized(result);
        response.setAccountNonExpired(userCredentials.isAccountNonExpired());
        response.setAccountNonLocked(userCredentials.isAccountNonLocked());
        response.setCredentialsNonExpired(userCredentials.isCredentialsNonExpired());
        response.setEnabled(userCredentials.isEnabled());
        return response;
    }

}
