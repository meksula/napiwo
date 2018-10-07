package pl.napiwo.scribe.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LoginObject implements Serializable {
    private String username;
    private String decryptedPassword;

    @Override
    public String toString() {
        return "Login credentials:\nusername : " + username + "\ndecryptedPassword : " + decryptedPassword;
    }

}
