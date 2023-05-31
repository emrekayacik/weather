package com.emrekayacik.weather.repository;

import com.emrekayacik.weather.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find the first User entity by username.
     *
     * @param username the username to search for
     * @return the User entity
     */
    User findFirstByUsername(String username);

    /**
     * Update the email of a User entity by username.
     *
     * @param username the username of the User entity
     * @param email    the new email value
     * @param date     the modified date
     */
    @Modifying
    @Transactional
    @Query("update User user set user.email = ?2, user.auditable.modifiedDate = ?3 where user.username = ?1")
    void updateEmailByUsername(String username, String email, LocalDateTime date);

    /**
     * Find a User entity by username.
     *
     * @param username the username to search for
     * @return the Optional containing the User entity, if found
     */
    Optional<User> findUserByUsername(String username);

}
