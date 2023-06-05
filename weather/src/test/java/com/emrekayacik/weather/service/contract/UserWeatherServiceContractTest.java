package com.emrekayacik.weather.service.contract;

import com.emrekayacik.weather.base.exception.custom.BusinessException;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.dto.UserWeatherDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.entity.UserWeather;
import com.emrekayacik.weather.mapper.UserWeatherMapper;
import com.emrekayacik.weather.mapper.UserWeatherMapperImpl;
import com.emrekayacik.weather.request.UserSaveCityByNameRequest;
import com.emrekayacik.weather.response.UserSaveCityByNameResponse;
import com.emrekayacik.weather.service.user.UserEntityService;
import com.emrekayacik.weather.service.userWeather.UserWeatherEntityService;
import com.emrekayacik.weather.service.userWeather.UserWeatherServiceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserWeatherServiceContractTest {
    @Mock
    private UserWeatherEntityService userWeatherEntityService;

    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserWeatherServiceContract userWeatherServiceContract;
    private UserWeatherMapper userWeatherMapper;

    @BeforeEach
    void setup() {
        userWeatherMapper = new UserWeatherMapperImpl();
    }

    @Test
    void save_ShouldSaveUserWeatherAndReturnResponse() {
        // Mock input
        String username = "testUser";
        String city = "testCity";
        Long id = 5L;

        // Create request-response
        UserSaveCityByNameRequest request = new UserSaveCityByNameRequest(username, city);
        UserSaveCityByNameResponse expectedResponse = new UserSaveCityByNameResponse(city,username,id);

        // Initialize user entity
        User userEntity = new User();
        userEntity.setUsername(username);


        // Initialize UserWeather entity
        UserWeather userWeather = new UserWeather();
        userWeather.setCityName(city);
        userWeather.setUser(userEntity);
        userWeather.setId(id);

        // Mock the behavior of userWeatherEntityService
        when(userWeatherEntityService.save(Mockito.any(UserWeather.class))).thenReturn(userWeather);

        // Call the method under test
        UserSaveCityByNameResponse actualResponse = userWeatherServiceContract.save(request);

        // Assert the result
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void should_findAll_when_zeroSize() {
        List<UserWeatherDto> userWeatherDtoList = userWeatherServiceContract.findAll();
        Assertions.assertEquals(0, userWeatherDtoList.size());
    }
    @Test
    void should_findAll_when_returns_singleRecord() {

        // Arrange and Mock the behaviour
        UserWeather userWeather = mock(UserWeather.class);
        User user = mock(User.class);

        String username = "emrekayacik";
        String city = "Istanbul";
        when(userWeather.getUser()).thenReturn(user);
        when(userWeather.getCityName()).thenReturn(city);
        when(userWeather.getUser().getUsername()).thenReturn(username);

        List<UserWeather> userWeatherList = new ArrayList<>();
        userWeatherList.add(userWeather);

        when(userWeatherEntityService.findAll()).thenReturn(userWeatherList);

        // Act
        List<UserWeatherDto> userWeatherDtoList = userWeatherServiceContract.findAll();

        // Assert
        Assertions.assertEquals(1, userWeatherDtoList.size());
        UserWeather result = userWeatherList.get(0);
        Assertions.assertEquals(username, result.getUser().getUsername());
        Assertions.assertEquals(city, result.getCityName());
    }
    @Test
    void should_findAll_when_returns_multipleUser() {

        // Arrange and Mock the behaviour

        UserWeather UserWeather = mock(UserWeather.class);
        UserWeather UserWeather1 = mock(UserWeather.class);

        List<UserWeather> userWeatherList = new ArrayList<>();
        userWeatherList.add(UserWeather);
        userWeatherList.add(UserWeather1);

        when(userWeatherEntityService.findAll()).thenReturn(userWeatherList);

        // Act
        List<UserWeatherDto> userWeatherDtoList = userWeatherServiceContract.findAll();
        // Assert
        Assertions.assertEquals(2, userWeatherDtoList.size());
    }

    @Test
    void should_findById() {
        // Arrange and Mock the behaviour

        UserWeather userWeather = mock(UserWeather.class);
        Long id = 1500L;

        when(userWeather.getId()).thenReturn(id);

        when(userWeatherEntityService.findById(id)).thenReturn(userWeather);

        // Act
        UserWeatherDto userWeatherDto = userWeatherServiceContract.findById(id);

        // Assert
        Assertions.assertEquals(id, userWeatherDto.getId());
    }
    @Test
    void should_findByUsername() {
        // Arrange
        String username = "emrekayacik";
        // Act
        List<UserWeatherDto> userWeatherDtoList = userWeatherServiceContract.findByUsername(username);
        // Assert
        Assertions.assertEquals(0, userWeatherDtoList.size());
    }
    @Test
    void should_findByCity() {
        // Assert
        String city = "istanbul";
        // Act
        List<UserWeatherDto> userWeatherDtoList = userWeatherServiceContract.findByCityName(city);
        // Assert
        Assertions.assertEquals(0, userWeatherDtoList.size());
    }
    @Test
    void should_findCurrentUserCities_throws_nullPointerException_when_noUsersAuthenticated() {
        Assertions.assertThrows(NullPointerException.class, () -> userWeatherServiceContract.findCurrentUserCities());
    }
    @Test
    void should_throws_businessException_when_noRecordFoundToDelete() {

        Mockito.doThrow(BusinessException.class).when(userWeatherEntityService).delete(Mockito.anyLong());

        Assertions.assertThrows(RuntimeException.class, () -> userWeatherServiceContract.delete(26L));

    }


    @Test
    void should_save_with_conversions() {
        // Arrange
        String username = "test";
        String cityName = "London";
        UserSaveCityByNameRequest request = mock(UserSaveCityByNameRequest.class);
        when(request.username()).thenReturn(username);
        when(request.city()).thenReturn(cityName);

        User user = new User();
        user.setUsername(username);
        when(userEntityService.findFirstByUsername(username)).thenReturn(user);

        UserDto userDto = new UserDto();
        userDto.setUsername(username);

        UserWeatherDto userWeatherDto = new UserWeatherDto(null, cityName, userDto);
        UserWeather userWeather = userWeatherMapper.convertToEntity(userWeatherDto);
        when(userWeatherEntityService.save(any(UserWeather.class))).thenReturn(userWeather);

        // Act
        UserSaveCityByNameResponse result = userWeatherServiceContract.save(request);

        // Assert
        assertNotNull(result);
        assertEquals(cityName, result.cityName());
        assertEquals(username, result.username());
        verify(userEntityService, times(1)).findFirstByUsername(username);
        verify(userWeatherEntityService, times(1)).save(any(UserWeather.class));
    }


}
