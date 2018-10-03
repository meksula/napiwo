package pl.napiwo.standalone.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author
 * Karol Meksuła
 * 03-10-2018
 * */

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    private long userProfileId;
    private String socialServiceId;

}
