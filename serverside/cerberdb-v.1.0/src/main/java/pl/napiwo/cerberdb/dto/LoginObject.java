package pl.napiwo.cerberdb.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

@Setter
@Getter
public class LoginObject implements Serializable {
    private String username;
    private String decryptedPassword;

    @Override
    public String toString() {
        return "Login credentials:\nusername : " + username + "\ndecryptedPassword : " + decryptedPassword;
    }

}
