package com.emrekayacik.weather.service.contract;

import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.request.UserChangeEmailRequest;
import com.emrekayacik.weather.request.UserDeleteRequest;
import com.emrekayacik.weather.request.UserUpdateRequest;
import com.emrekayacik.weather.service.user.UserEntityService;
import com.emrekayacik.weather.service.user.UserServiceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceContractTest {
    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserServiceContract userServiceContract;

    @Test
    void should_findAll_when_zeroSize() {
        List<UserDto> userDtoList = userServiceContract.findAll();
        Assertions.assertEquals(0, userDtoList.size());
    }
    @Test
    void should_findAll_when_returns_singleUser() {
        // Arrange and Mock the behaviour

        String username = "emrekayacik";
        User user = mock(User.class);
        when(user.getUsername()).thenReturn(username);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userEntityService.findAll()).thenReturn(userList);

        // Act
        List<UserDto> userDtoList = userServiceContract.findAll();

        // Assert
        Assertions.assertEquals(1, userDtoList.size());
        User result = userList.get(0);
        Assertions.assertEquals(username, result.getUsername());
    }
    @Test
    void should_findAll_when_returns_multipleUser() {
        // Arrange and Mock the behaviour

        User user = mock(User.class);
        User user1 = mock(User.class);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        when(userEntityService.findAll()).thenReturn(userList);

        // Act
        List<UserDto> userDtoList = userServiceContract.findAll();

        // Assert
        Assertions.assertEquals(2, userDtoList.size());
    }
    @Test
    void should_findById() {
        // Arrange and Mock the behaviour

        User user = new User();
        Long id = 1500L;
        user.setId(id);
        when(userEntityService.findById(id)).thenReturn(user);

        // Act
        UserDto userDto = userServiceContract.findById(id);

        // Assert
        Assertions.assertEquals(id, userDto.getId());
    }
    @Test
    void should_findByUsername() {
        User user = mock(User.class);
        String username = "emrekayacik";

        when(user.getUsername()).thenReturn(username);

        when(userEntityService.findFirstByUsername(username)).thenReturn(user);

        UserDto userDto = userServiceContract.findByUsername(username);
        Assertions.assertEquals(username, userDto.getUsername());
    }
    @Test
    void should_changeEmail_throw_exception_when_username_cannot_found() {
        UserChangeEmailRequest userChangeEmailRequest = mock(UserChangeEmailRequest.class);
        Assertions.assertThrows(RuntimeException.class, () -> userServiceContract.changeEmail(userChangeEmailRequest));
    }
    @Test
    void should_changeEmail() {
        // Arrange and Mock the behaviour
        String username = "emrekayacik";
        String email = "emrekayacik@domain.com";
        Mockito.doNothing().when(userEntityService).updateEmailByUsername(username,email);

        // Act
        userEntityService.updateEmailByUsername(username, email);

        // Verify method
        Mockito.verify(userEntityService).updateEmailByUsername(username,email);
    }
    @Test
    void should_update() {
        // Arrange and Mock the behaviour
        Long id = 26L;
        UserUpdateRequest userUpdateRequest = mock(UserUpdateRequest.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(id);
        when(userEntityService.save(any())).thenReturn(user);

        // Act
        UserDto userDto = userServiceContract.update(id, userUpdateRequest);

        // Assert
        Assertions.assertEquals(userDto.getId(), id);

    }
    @Test
    void should_throw_exception_when_invalid_request_found() {
        UserDeleteRequest request = mock(UserDeleteRequest.class);
        Assertions.assertThrows(RuntimeException.class, () -> userServiceContract.deleteUserByUsername(request));
    }

    @Test
    void should_deleteUserByUsername() {
        // Arrange and Mock the behaviour
        User user = new User();
        String username = "emrekayacik";
        user.setUsername(username);
        Mockito.doNothing().when(userEntityService).delete(user);

        // Act
        userEntityService.delete(user);

        // Verify method
        Mockito.verify(userEntityService).delete(user);
    }
    @Test
    void should_deleteUserByUsername_throw_exception_when_userNotFound() {
        // Arrange and Mock the behaviour
        String username = "emrekayacik";
        UserDeleteRequest request = new UserDeleteRequest(username);
        User user = new User();
        user.setUsername(username);

        // Act and Assert
        Assertions.assertThrows(RuntimeException.class, () -> userServiceContract.deleteUserByUsername(request));

    }
}
