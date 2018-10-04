package pl.napiwo.standalone.services.implementation

import groovy.util.logging.Slf4j
import org.codehaus.jackson.map.ObjectMapper
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.oauth2.provider.OAuth2Authentication
import pl.napiwo.standalone.dto.UserProfile
import pl.napiwo.standalone.repository.UserProfileRepository
import pl.napiwo.standalone.services.UserExistService
import spock.lang.Specification

/**
 * @author
 * Karol Meksuła
 * 03-10-2018
 * */

@SpringBootTest
@Slf4j
class UserExistServiceDefaultTest extends Specification {
    final OAuth2Authentication auth2Authentication = Mockito.mock(OAuth2Authentication.class)

    @Autowired
    private UserExistService userExistService

    @Autowired
    private UserProfileRepository userProfileRepository

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

    def "method should return true: user exist in database and authentication is correct"() {
        setup:
        def name = "John Doe"
        def id = "3383381038948"
        def userProfile = new UserProfile()
        userProfile.setSocialServiceId(id)
        userProfile.setSocialUserName(name)
        log.debug(new ObjectMapper().writeValueAsString(userProfile))
        userProfileRepository.save(userProfile)

        expect:
        userExistService.isUserExist(id, name)

        cleanup:
        userProfileRepository.deleteAll()
    }

    def "method should return false: user NOT exist in database BUT authentication is correct"() {
        setup:
        def name = "John Doe"
        def id = "3383381038948"
        def userProfile = new UserProfile()
        userProfile.setSocialServiceId(id)
        userProfile.setSocialUserName(name)

        expect:
        !userExistService.isUserExist(id, name)
    }

}
