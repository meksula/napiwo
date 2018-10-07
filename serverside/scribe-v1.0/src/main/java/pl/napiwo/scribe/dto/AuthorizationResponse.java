package pl.napiwo.scribe.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AuthorizationResponse implements Serializable {
    private String username;
    private String authDate;
    private String authType;
    private List<String> authorities;
    private boolean isAuthorized;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @Override
    public String toString() {
        return username + " is authorized = " + isAuthorized;
    }

}
