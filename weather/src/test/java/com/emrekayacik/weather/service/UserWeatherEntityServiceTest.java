package com.emrekayacik.weather.service;

import com.emrekayacik.weather.base.exception.custom.ItemNotFoundException;
import com.emrekayacik.weather.entity.UserWeather;
import com.emrekayacik.weather.repository.UserWeatherRepository;
import com.emrekayacik.weather.service.userWeather.UserWeatherEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserWeatherEntityServiceTest {

    private UserWeatherEntityService userWeatherEntityService;

    @Mock
    private UserWeatherRepository userWeatherRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userWeatherEntityService = new UserWeatherEntityService(userWeatherRepositoryMock);
    }

    @Test
    void should_save_when_validEntity() {
        // Arrange
        UserWeather userWeather = new UserWeather();

        when(userWeatherRepositoryMock.save(userWeather)).thenReturn(userWeather);

        // Act
        UserWeather savedUserWeather = userWeatherEntityService.save(userWeather);

        // Assert
        assertNotNull(savedUserWeather);
        assertEquals(userWeather, savedUserWeather);
        verify(userWeatherRepositoryMock, times(1)).save(userWeather);
    }

    @Test
    void should_findAll_when_multipleEntities_present() {
        // Arrange
        UserWeather userWeather1 = new UserWeather();
        UserWeather userWeather2 = new UserWeather();
        List<UserWeather> expectedUserWeathers = Arrays.asList(userWeather1, userWeather2);
        when(userWeatherRepositoryMock.findAll()).thenReturn(expectedUserWeathers);

        // Act
        List<UserWeather> actualUserWeathers = userWeatherEntityService.findAll();

        // Assert
        assertEquals(expectedUserWeathers, actualUserWeathers);
        verify(userWeatherRepositoryMock, times(1)).findAll();
    }

    @Test
    void should_deleteById_when_existingId() {
        // Arrange
        Long userWeatherId = 1L;

        // Act
        userWeatherEntityService.delete(userWeatherId);

        // Assert
        verify(userWeatherRepositoryMock, times(1)).deleteById(userWeatherId);
    }

    @Test
    void should_delete_when_existingEntity() {
        // Arrange
        UserWeather userWeather = new UserWeather();

        // Act
        userWeatherEntityService.delete(userWeather);

        // Assert
        verify(userWeatherRepositoryMock, times(1)).delete(userWeather);
    }

    @Test
    void should_findById_when_existingId() {
        // Arrange
        Long userWeatherId = 1L;
        UserWeather expectedUserWeather = new UserWeather();
        when(userWeatherRepositoryMock.findById(userWeatherId)).thenReturn(Optional.of(expectedUserWeather));

        // Act
        UserWeather actualUserWeather = userWeatherEntityService.findById(userWeatherId);

        // Assert
        assertEquals(expectedUserWeather, actualUserWeather);
        verify(userWeatherRepositoryMock, times(1)).findById(userWeatherId);
    }

    @Test
    void should_findById_throwItemNotFoundException_when_nonExistingId() {
        // Arrange
        Long nonExistingId = 999L;
        when(userWeatherRepositoryMock.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ItemNotFoundException.class, () -> userWeatherEntityService.findById(nonExistingId));
        verify(userWeatherRepositoryMock, times(1)).findById(nonExistingId);
    }
}
