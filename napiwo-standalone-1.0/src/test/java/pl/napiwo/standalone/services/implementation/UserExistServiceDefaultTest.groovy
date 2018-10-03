package pl.napiwo.standalone.services.implementation

import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.oauth2.provider.OAuth2Authentication
import pl.napiwo.standalone.services.UserExistService
import spock.lang.Specification

/**
 * @author
 * Karol Meksuła
 * 03-10-2018
 * */

@SpringBootTest
class UserExistServiceDefaultTest extends Specification {
    final OAuth2Authentication auth2Authentication = Mockito.mock(OAuth2Authentication.class)

    @Autowired
    private UserExistService userExistService

    void setup() {
    }

    def "initialize should be correct"() {
        expect:
        userExistService != null
    }

    def "method should throw exception: no user in database OR invalid social service response"() {
        when:
        userExistService.isUserExist(auth2Authentication)

        then:
        def ex = thrown(RuntimeException)
        ex.getMessage() == "Something went wrong. Cannot fetch UserProfile.class entity."
    }

}
