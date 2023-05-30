package com.emrekayacik.weather.service.contract;

import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.dto.UserWeatherDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.entity.UserWeather;
import com.emrekayacik.weather.request.UserSaveCityByNameRequest;
import com.emrekayacik.weather.response.UserSaveCityByNameResponse;
import com.emrekayacik.weather.service.user.UserEntityService;
import com.emrekayacik.weather.service.userWeather.UserWeatherEntityService;
import com.emrekayacik.weather.service.userWeather.UserWeatherServiceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserWeatherServiceContractTest {
    @Mock
    private UserWeatherEntityService userWeatherEntityService;

    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserWeatherServiceContract userWeatherServiceContract;

    @Test
    void save_ShouldSaveUserWeatherAndReturnResponse() {
        // Mock input
        String username = "testUser";
        String city = "testCity";

        // Create request-response
        UserSaveCityByNameRequest request = new UserSaveCityByNameRequest(username, city);
        UserSaveCityByNameResponse expectedResponse = new UserSaveCityByNameResponse(city,username);

        // Initialize user entity
        User userEntity = new User();
        userEntity.setUsername(username);


        // Initialize UserWeather entity
        UserWeather userWeather = new UserWeather();
        userWeather.setCityName(city);
        userWeather.setUser(userEntity);

        // Mock the behavior of userWeatherEntityService
        when(userWeatherEntityService.save(Mockito.any(UserWeather.class))).thenReturn(userWeather);

        // Call the method under test
        UserSaveCityByNameResponse actualResponse = userWeatherServiceContract.save(request);

        // Assert the result
        assertEquals(expectedResponse, actualResponse);
    }
}
