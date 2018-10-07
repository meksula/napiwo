package pl.napiwo.cerberdb.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

/**
 * This class represent JSON response which every request to database must have.
 * */
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserAccessKey implements Serializable {
    private long userProfileId;
    private String decryptedAccessToken;
}
