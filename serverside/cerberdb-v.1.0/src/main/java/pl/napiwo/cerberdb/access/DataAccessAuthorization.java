package pl.napiwo.cerberdb.access;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.napiwo.cerberdb.dto.UserAccessEntity;
import pl.napiwo.cerberdb.dto.UserAccessKey;
import pl.napiwo.cerberdb.exception.CerberdbDataNotFound;
import pl.napiwo.cerberdb.repository.UserAccessEntityRepository;

import java.time.LocalDateTime;

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
        String encryptedToken = fetchUserAccessEntity(userAccessKey).getEncryptedAccessToken();
        return passwordEncoder.matches(userAccessKey.getDecryptedAccessToken(), encryptedToken);
    }

    private UserAccessEntity fetchUserAccessEntity(UserAccessKey userAccessKey) {
        return userAccessEntityRepository.findByUserProfileId(userAccessKey.getUserProfileId())
                .orElseThrow(() -> new CerberdbDataNotFound("UserAccessEntity.class",
                        String.valueOf(userAccessKey.getUserProfileId())));
    }

    @Override
    public UserAccessEntity encryptAndSave(UserAccessKey userAccessKey) {
        String encryptedToken = passwordEncoder.encode(userAccessKey.getDecryptedAccessToken());

        UserAccessEntity userAccessEntity;
        try {
            userAccessEntity = fetchUserAccessEntity(userAccessKey);
        } catch (CerberdbDataNotFound cerberdbDataNotFound) {
            userAccessEntity = new UserAccessEntity();
            userAccessEntity.setUserProfileId(userAccessKey.getUserProfileId());
        }

        userAccessEntity.setEncryptedAccessToken(encryptedToken);
        return userAccessEntityRepository.save(tokenRefreshDatastamp(userAccessEntity));
    }

    private UserAccessEntity tokenRefreshDatastamp(UserAccessEntity userAccessEntity) {
        String datastamp = LocalDateTime.now().toString();
        userAccessEntity.setLastGeneratedTokenDate(datastamp);
        return userAccessEntity;
    }

}
