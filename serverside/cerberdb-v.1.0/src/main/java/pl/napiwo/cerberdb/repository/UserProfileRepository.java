package pl.napiwo.cerberdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.napiwo.cerberdb.dto.UserProfile;

import java.util.Optional;

/**
 * @author
 * Karol Meksuła
 * 03-10-2018
 * */

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findBySocialServiceId(String socialServiceId);

    Optional<UserProfile> findBySocialServiceIdAndAndSocialUserName(String socialServiceId, String socialUsername);
}
