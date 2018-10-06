package pl.napiwo.cerberdb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author
 * Karol Meksuła
 * 03-10-2018
 * */

@Entity
@Table(name = "user_profile")
@Getter
@Setter
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userProfileId;
    private String socialServiceId;
    private String socialUserName;

    @Override
    public String toString() {
        return "userProfileId : [" + userProfileId + "]," +
                "\nsocialServiceId : [" + socialServiceId + "]," +
                "\nsocialUserName : [" + socialUserName + "]";
    }

    @Override
    public int hashCode() {
        return (int) (userProfileId + socialServiceId.hashCode() + socialUserName.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserProfile) {
            UserProfile other = (UserProfile) obj;
            return this.userProfileId == other.getUserProfileId()
                    && this.socialUserName.equals(other.getSocialUserName());
        }

        return false;
    }

}
