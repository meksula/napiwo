package pl.napiwo.cerberdb.access

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import pl.napiwo.cerberdb.dto.UserAccessEntity
import pl.napiwo.cerberdb.dto.UserAccessKey
import pl.napiwo.cerberdb.exception.CerberdbDataNotFound
import pl.napiwo.cerberdb.repository.UserAccessEntityRepository
import spock.lang.Specification

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

@SpringBootTest
class DataAccessAuthorizationTest extends Specification {

    @Autowired
    private DataAccessAuthorization dataAccessAuthorization

    @Autowired
    private UserAccessEntityRepository userAccessEntityRepository

    @Autowired
    private BCryptPasswordEncoder passwordEncoder

    private final long INVALID_USER_ID = 9237489728947
    private final long VALID_USER_ID = 219489494894
    private final String DECRYPTED_PASSWORD_INVALID = "389d73d73x7b27d762bdn0-c.c3"
    private final String DECRYPTED_PASSWORD_VALID = "23-0d2k0cx2kx902k390"

    private UserAccessKey invalidAccessKey
    private UserAccessKey validAccessKey

    void setup() {
        invalidAccessKey = new UserAccessKey()
        invalidAccessKey.setUserProfileId(INVALID_USER_ID)
        invalidAccessKey.setDecryptedAccessToken(DECRYPTED_PASSWORD_INVALID)

        validAccessKey = new UserAccessKey()
        validAccessKey.setUserProfileId(VALID_USER_ID)
        validAccessKey.setDecryptedAccessToken(DECRYPTED_PASSWORD_VALID)
    }

    def "method should return true : authorization valid"() {
        setup:
        def userAccessEntity = new UserAccessEntity()
        userAccessEntity.setUserProfileId(VALID_USER_ID)
        userAccessEntity.setEncryptedAccessToken(passwordEncoder.encode(DECRYPTED_PASSWORD_VALID))
        userAccessEntityRepository.save(userAccessEntity)

        expect:
        dataAccessAuthorization.isAuthorized(validAccessKey)
    }

    def "method should return false : authorization invalid"() {
        setup:
        def userAccessEntity = new UserAccessEntity()
        userAccessEntity.setUserProfileId(VALID_USER_ID)
        userAccessEntity.setEncryptedAccessToken(passwordEncoder.encode(DECRYPTED_PASSWORD_VALID))
        userAccessEntityRepository.save(userAccessEntity)

        expect:
        validAccessKey.setDecryptedAccessToken(DECRYPTED_PASSWORD_INVALID)
        !dataAccessAuthorization.isAuthorized(validAccessKey)
    }

    def "method should throw exception : no such user in database"() {
        when:
        dataAccessAuthorization.isAuthorized(invalidAccessKey)

        then:
        thrown(CerberdbDataNotFound)
    }

    void cleanup() {
        userAccessEntityRepository.deleteAll()
    }

}
