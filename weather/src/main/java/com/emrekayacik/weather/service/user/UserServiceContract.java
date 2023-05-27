package com.emrekayacik.weather.service.user;

import com.emrekayacik.weather.base.exception.custom.ItemNotFoundException;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.mapper.UserMapper;
import com.emrekayacik.weather.request.UserChangeEmailRequest;
import com.emrekayacik.weather.request.UserDeleteRequest;
import com.emrekayacik.weather.request.UserUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Contract class for managing User related operations.
 * This class provides mappings and conversions between DTOs, requests and entities
 * and uses the UserEntityService for database operations.
 */
@Service
@RequiredArgsConstructor
public class UserServiceContract {
    private final UserEntityService userEntityService;
    private final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Retrieves all users.
     *
     * @return a list of UserDto objects representing the users
     */
    public List<UserDto> findAll() {
        List<User> userList = userEntityService.findAll();
        return INSTANCE.convertToDtoList(userList);
    }

    /**
     * Finds a user by ID.
     *
     * @param id the ID of the user
     * @return the UserDto object representing the user
     * @throws ItemNotFoundException if the user is not found
     */
    public UserDto findById(Long id) {
        User user = userEntityService.findById(id);
        if (user == null) {
            throw new ItemNotFoundException(() ->
                    "User cannot be found with ID: " + id
            );
        }
        return INSTANCE.convertToDto(user);
    }

    /**
     * Finds a user by username.
     *
     * @param username the username of the user
     * @return the UserDto object representing the user
     * @throws ItemNotFoundException if the user is not found
     */
    public UserDto findByUsername(String username) {
        User user = userEntityService.findFirstByUsername(username);
        if (user == null) {
            throw new ItemNotFoundException(() ->
                    "User cannot be found with username: " + username
            );
        }
        return INSTANCE.convertToDto(user);
    }

    /**
     * Deletes a user by username.
     *
     * @param userDeleteRequest the UserDeleteRequest object containing the username
     * @throws ItemNotFoundException if the user is not found
     */
    @Transactional
    public void deleteUserByUsername(UserDeleteRequest userDeleteRequest) {
        UserDto userByUsername = findByUsername(userDeleteRequest.username());
        User user = INSTANCE.convertToEntity(userByUsername);
        userEntityService.delete(user);
    }

    /**
     * Changes the email of a user.
     *
     * @param request the UserChangeEmailRequest object containing the username and new email
     * @throws ItemNotFoundException if the user is not found
     */
    @Transactional
    public void changeEmail(UserChangeEmailRequest request) {
        findByUsername(request.username());
        userEntityService.updateEmailByUsername(request.username(), request.email());
    }

    /**
     * Updates a user.
     *
     * @param id      the ID of the user to be updated
     * @param request the UserUpdateRequest object containing the updated user data
     * @return the UserDto object representing the updated user
     */
    public UserDto update(Long id, UserUpdateRequest request) {
        UserDto userDto = INSTANCE.convertUpdateRequestToDto(request);
        userDto.setId(id);

        User savedUser = userEntityService.save(INSTANCE.convertToEntity(userDto));
        return INSTANCE.convertToDto(savedUser);
    }
}
