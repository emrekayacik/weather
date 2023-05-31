package com.emrekayacik.weather.service;

import com.emrekayacik.weather.base.exception.custom.BusinessException;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.repository.UserRepository;
import com.emrekayacik.weather.service.user.UserEntityService;
import com.emrekayacik.weather.service.user.UserServiceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserEntityService userEntityService;

    @Test
    void should_findAll() {
        List<User> users = userEntityService.findAll();
        Assertions.assertEquals(0, users.size());
    }

    @Test
    void should_findFirstByUserName_return_correct_username() {
        String username = "emrekayacik";

        User user = new User();
        user.setUsername(username);

        when(userRepository.findFirstByUsername(any())).thenReturn(user);


        User actualUser = userEntityService.findFirstByUsername(username);

        Assertions.assertEquals(username, actualUser.getUsername());
    }

    @Test
    void should_updateEmailByUsername_return_nothing() {
        String username = "emrekayacik";
        String email = "emrekayacik2634@gmail.com";

        Mockito.doNothing().when(userRepository).updateEmailByUsername(any(),any(),any());
        userEntityService.updateEmailByUsername(username,email);
    }


}
