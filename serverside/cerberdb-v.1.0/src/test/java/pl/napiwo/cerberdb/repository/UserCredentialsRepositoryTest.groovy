package pl.napiwo.cerberdb.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import pl.napiwo.cerberdb.authorization.AuthorizationType
import pl.napiwo.cerberdb.dto.UserCredentials
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

@SpringBootTest
class UserCredentialsRepositoryTest extends Specification {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository

    @Autowired
    private BCryptPasswordEncoder passwordEncoder

    private UserCredentials userCredentials

    void setup() {
        this.userCredentials = new UserCredentials()
        this.userCredentials.setAuthorizationType(AuthorizationType.SCRIBE)
        this.userCredentials.setUserProfileId(999929348923)
        this.userCredentials.setUsername("karoladmin")
        this.userCredentials.setEncryptedPassword(passwordEncoder.encode("karoladmin"))
        this.userCredentials.setEmail("karol.admin@gmail.com")
        this.userCredentials.setJoinDate(LocalDateTime.now().toString())
        this.userCredentials.setAuthorities(["common_user"])
    }

    def "entity should be correctly save to database"() {
        when:
        userCredentialsRepository.save(userCredentials)

        then:
        userCredentialsRepository.findByUsername("karoladmin").isPresent()
    }

    void cleanup() {
        userCredentialsRepository.deleteAll()
    }

}
