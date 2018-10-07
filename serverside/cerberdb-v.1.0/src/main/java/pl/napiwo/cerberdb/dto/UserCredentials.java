package pl.napiwo.cerberdb.dto;

import lombok.Getter;
import lombok.Setter;
import pl.napiwo.cerberdb.authorization.AuthorizationType;

import javax.persistence.*;
import java.util.List;

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

@Getter
@Setter
@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userCredentialId;

    private AuthorizationType authorizationType;
    private long userProfileId;
    private String socialServiceId;
    private String socialUserName;
    private String username;
    private String encryptedPassword;
    private String email;
    private String joinDate;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "userCredentialId"))
    private List<String> authorities;
}
