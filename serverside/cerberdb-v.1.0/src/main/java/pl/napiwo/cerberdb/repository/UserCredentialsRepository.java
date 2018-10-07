package pl.napiwo.cerberdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.napiwo.cerberdb.dto.UserCredentials;

import java.util.Optional;

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    Optional<UserCredentials> findByUsername(String username);
}
