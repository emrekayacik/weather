package com.emrekayacik.weather.service;

import com.emrekayacik.weather.base.exception.custom.OpenWeatherClientException;
import com.emrekayacik.weather.client.openweather.client.WeatherClient;
import com.emrekayacik.weather.client.openweather.response.current.CurrentWeatherResponse;
import com.emrekayacik.weather.client.openweather.response.forecast.ForecastResponse;
import com.emrekayacik.weather.service.client.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {
    @Mock
    private WeatherClient weatherClient;

    @Value("${openweathermap.apiKey}")
    private String apiKey;

    @Value("${openweathermap.unit}")
    private String unit;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientService = new ClientService(weatherClient);
    }

    @Test
    void should_getCurrentWeatherByCityName_ReturnsCurrentWeatherResponse_when_ValidCity() {
        // Arrange
        String city = "London";
        CurrentWeatherResponse expectedResponse = new CurrentWeatherResponse();

        // Mock the behavior of weatherClient.getCurrentWeatherByCityName()
        when(weatherClient.getCurrentWeatherByCityName(eq(city), eq(unit), eq(apiKey)))
                .thenReturn(expectedResponse);

        // Act
        CurrentWeatherResponse actualResponse = clientService.getCurrentWeatherByCityName(city);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(weatherClient, times(1)).getCurrentWeatherByCityName(eq(city), eq(unit), eq(apiKey));
    }

    @Test
    void should_getCurrentWeatherByCoordinates_ReturnsCurrentWeatherResponse_when_ValidCoordinates() {
        // Arrange
        double lat = 51.5074;
        double lon = -0.1278;
        CurrentWeatherResponse expectedResponse = new CurrentWeatherResponse();

        // Mock the behavior of weatherClient.getCurrentWeatherByCoordinates()
        when(weatherClient.getCurrentWeatherByCoordinates(eq(lat), eq(lon), eq(unit), eq(apiKey)))
                .thenReturn(expectedResponse);

        // Act
        CurrentWeatherResponse actualResponse = clientService.getCurrentWeatherByCoordinates(lat, lon);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(weatherClient, times(1)).getCurrentWeatherByCoordinates(eq(lat), eq(lon), eq(unit), eq(apiKey));
    }

    @Test
    void should_getCurrentWeatherByCoordinates_ThrowsException_when_InvalidLatitude() {
        // Arrange
        double lat = 91.0;
        double lon = -0.1278;

        // Act & Assert
        assertThrows(OpenWeatherClientException.class,
                () -> clientService.getCurrentWeatherByCoordinates(lat, lon),
                "Latitude must be between (-90,90)");
    }

    @Test
    void should_getCurrentWeatherByCoordinates_ThrowsException_when_InvalidLongitude() {
        // Arrange
        double lat = 51.5074;
        double lon = 181.0;

        // Act & Assert
        assertThrows(OpenWeatherClientException.class,
                () -> clientService.getCurrentWeatherByCoordinates(lat, lon),
                "Longitude must be between (-180,180)");
    }

    @Test
    void should_getWeatherFiveDaysForecastsByCityName_ReturnsForecastResponse_when_ValidCity() {
        // Arrange
        String city = "London";
        ForecastResponse expectedResponse = new ForecastResponse();

        // Mock the behavior of weatherClient.getWeatherFiveDaysForecastsByCityName()
        when(weatherClient.getWeatherFiveDaysForecastsByCityName(eq(city), eq(unit), eq(apiKey)))
                .thenReturn(expectedResponse);

        // Act
        ForecastResponse actualResponse = clientService.getWeatherFiveDaysForecastsByCityName(city);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(weatherClient, times(1)).getWeatherFiveDaysForecastsByCityName(eq(city), eq(unit), eq(apiKey));
    }

    @Test
    void should_getWeatherFiveDaysForecastsByCoordinates_ReturnsForecastResponse_when_ValidCoordinates() {
        // Arrange
        double lat = 51.5074;
        double lon = -0.1278;
        ForecastResponse expectedResponse = new ForecastResponse();

        // Mock the behavior of weatherClient.getWeatherFiveDaysForecastsByCoordinates()
        when(weatherClient.getWeatherFiveDaysForecastsByCoordinates(eq(lat), eq(lon), eq(unit), eq(apiKey)))
                .thenReturn(expectedResponse);

        // Act
        ForecastResponse actualResponse = clientService.getWeatherFiveDaysForecastsByCoordinates(lat, lon);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(weatherClient, times(1)).getWeatherFiveDaysForecastsByCoordinates(eq(lat), eq(lon), eq(unit), eq(apiKey));
    }

    @Test
    void should_getWeatherFiveDaysForecastsByCoordinates_ThrowsException_when_InvalidLatitude() {
        // Arrange
        double lat = 91.0;
        double lon = -0.1278;

        // Act & Assert
        assertThrows(OpenWeatherClientException.class,
                () -> clientService.getWeatherFiveDaysForecastsByCoordinates(lat, lon),
                "Latitude must be between (-90,90)");
    }

    @Test
    void should_getWeatherFiveDaysForecastsByCoordinates_ThrowsException_when_InvalidLongitude() {
        // Arrange
        double lat = 51.5074;
        double lon = 181.0;

        // Act & Assert
        assertThrows(OpenWeatherClientException.class,
                () -> clientService.getWeatherFiveDaysForecastsByCoordinates(lat, lon),
                "Longitude must be between (-180,180)");
    }

}
