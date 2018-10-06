package pl.napiwo.cerberdb.access;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.napiwo.cerberdb.dto.UserAccessEntity;
import pl.napiwo.cerberdb.dto.UserAccessKey;
import pl.napiwo.cerberdb.exception.CerberdbDataNotFound;
import pl.napiwo.cerberdb.repository.UserAccessEntityRepository;

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

@Service
public class DataAccessAuthorization implements DataAccess {
    private BCryptPasswordEncoder passwordEncoder;
    private UserAccessEntityRepository userAccessEntityRepository;

    public DataAccessAuthorization(BCryptPasswordEncoder passwordEncoder, UserAccessEntityRepository userAccessEntityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userAccessEntityRepository = userAccessEntityRepository;
    }

    @Override
    public boolean isAuthorized(UserAccessKey userAccessKey) {
        UserAccessEntity userAccessEntity = userAccessEntityRepository.findByUserProfileId(userAccessKey.getUserProfileId())
                .orElseThrow(() -> new CerberdbDataNotFound("UserAccessEntity.class",
                        String.valueOf(userAccessKey.getUserProfileId())));
        String encryptedToken = userAccessEntity.getEncryptedAccessToken();
        return passwordEncoder.matches(userAccessKey.getDecryptedAccessToken(), encryptedToken);
    }

}
