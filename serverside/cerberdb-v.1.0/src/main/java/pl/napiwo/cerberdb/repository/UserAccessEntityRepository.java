package pl.napiwo.cerberdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.napiwo.cerberdb.dto.UserAccessEntity;

import java.util.Optional;

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

public interface UserAccessEntityRepository extends JpaRepository<UserAccessEntity, Long> {
    Optional<UserAccessEntity> findByUserProfileId(long userProfileId);
}
