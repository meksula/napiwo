package pl.napiwo.standalone.dto

import spock.lang.Specification

/**
 * @author
 * Karol Meksuła
 * 04-10-2018
 * */

class UserProfileTest extends Specification {
    UserProfile userProfile
    def userProfileId = 423423424
    def socialServiceId = "9849284902"
    def socialUserName = "John Doe"

    void setup() {
        userProfile = new UserProfile()
        userProfile.setUserProfileId(userProfileId)
        userProfile.setSocialServiceId(socialServiceId)
        userProfile.setSocialUserName(socialUserName)
    }

    def "toString method test"() {
        expect:
        println(userProfile.toString())
    }

    def "hashCode method test"() {
        expect:
        println(userProfile.hashCode())
    }

    def "equals method test"() {
        setup:
        def otherSame = new UserProfile()
        otherSame.setUserProfileId(userProfileId)
        otherSame.setSocialServiceId(socialServiceId)
        otherSame.setSocialUserName(socialUserName)

        expect:
        !userProfile.equals(new UserProfile())
        userProfile.equals(otherSame)
    }
}
