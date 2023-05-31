package com.emrekayacik.weather.service.contract;

import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.request.UserDeleteRequest;
import com.emrekayacik.weather.service.user.UserEntityService;
import com.emrekayacik.weather.service.user.UserServiceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        String username = "emrekayacik";

        User user = mock(User.class);

        when(user.getUsername()).thenReturn(username);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userEntityService.findAll()).thenReturn(userList);

        List<UserDto> userDtoList = userServiceContract.findAll();

        Assertions.assertEquals(1, userDtoList.size());
        User result = userList.get(0);
        Assertions.assertEquals(username, result.getUsername());
    }
    @Test
    void should_findAll_when_returns_multipleUser() {

        User user = mock(User.class);
        User user1 = mock(User.class);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        when(userEntityService.findAll()).thenReturn(userList);

        List<UserDto> userDtoList = userServiceContract.findAll();

        Assertions.assertEquals(2, userDtoList.size());
    }
    @Test
    void should_throw_exception_when_invalid_request_found() {
        UserDeleteRequest request = mock(UserDeleteRequest.class);
        Assertions.assertThrows(RuntimeException.class, () -> userServiceContract.deleteUserByUsername(request));
    }


    @Test
    void should_throw_exception_when_username_not_found() {
        String username = "emrekayacik";
        UserDeleteRequest request = new UserDeleteRequest(username);
        User user = new User();
        user.setUsername(username);

        Assertions.assertThrows(RuntimeException.class, () -> userServiceContract.deleteUserByUsername(request));

    }
}
