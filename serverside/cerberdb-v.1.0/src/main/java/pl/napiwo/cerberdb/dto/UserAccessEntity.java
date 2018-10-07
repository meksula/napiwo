package pl.napiwo.cerberdb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

/**
 * This DTO class represents access object to every microservices in na-piwo.pl
 * */
@Entity
@Table(name = "user_access_entity")
@Getter
@Setter
public class UserAccessEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userProfileId;
    private String encryptedAccessToken;
    private String lastGeneratedTokenDate;

}
