package com.emrekayacik.weather.service.user;

import com.emrekayacik.weather.base.service.BaseEntityService;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
/**
 * Service class for performing operations on User entities.
 */
@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {
    private final UserRepository userRepository;

    /**
     * Constructs a new UserEntityService with the specified UserRepository.
     *
     * @param repository the UserRepository to be used for database operations
     */
    public UserEntityService(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    /**
     * Finds the first user with the specified username.
     *
     * @param username the username to search for
     * @return the user entity if found, null otherwise
     */
    public User findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    /**
     * Updates the email of the user with the specified username.
     *
     * @param username the username of the user to update
     * @param email    the new email value
     */
    public void updateEmailByUsername(String username, String email) {
        userRepository.updateEmailByUsername(username, email, LocalDateTime.now());
    }
}
