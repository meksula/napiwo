package pl.napiwo.cerberdb.dto;

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represent JSON response which every request to database must have.
 * */

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserAccessKey {
    private long userProfileId;
    private String decryptedAccessToken;
}
