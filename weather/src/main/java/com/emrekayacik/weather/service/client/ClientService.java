
package com.emrekayacik.weather.service.client;

import com.emrekayacik.weather.base.exception.custom.OpenWeatherClientException;
import com.emrekayacik.weather.client.openweather.client.WeatherClient;
import com.emrekayacik.weather.client.openweather.response.current.CurrentWeatherResponse;
import com.emrekayacik.weather.client.openweather.response.forecast.ForecastResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * Service class that interacts with the OpenWeatherMap API client.
 */

@Service
@RequiredArgsConstructor
public class ClientService {
    private final WeatherClient weatherClient;
    @Value("${openweathermap.apiKey}")
    String apiKey;
    @Value("${openweathermap.unit}")
    String unit;

    /**
     * Retrieves the current weather by city name.
     *
     * @param city The name of the city.
     * @return The {@link CurrentWeatherResponse} containing the current weather information.
     */
    public CurrentWeatherResponse getCurrentWeatherByCityName(String city) {
        return weatherClient.getCurrentWeatherByCityName(city, unit, apiKey);
    }

    /**
     * Retrieves the current weather by coordinates.
     *
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return The {@link CurrentWeatherResponse} containing the current weather information.
     */
    public CurrentWeatherResponse getCurrentWeatherByCoordinates(double lat, double lon) {
        latitudeRangeCheck(lat, 90, "Latitude must be between (-90,90) ");
        latitudeRangeCheck(lon, 180, "Longitude must be between (-180,180) ");
        return weatherClient.getCurrentWeatherByCoordinates(lat, lon, unit, apiKey);
    }

    /**
     * Retrieves the weather forecast for the next five days by city name.
     *
     * @param city The name of the city.
     * @return The {@link ForecastResponse} containing the weather forecast information.
     */
    public ForecastResponse getWeatherFiveDaysForecastsByCityName(String city) {
        return weatherClient.getWeatherFiveDaysForecastsByCityName(city, unit, apiKey);
    }

    /**
     * Retrieves the weather forecast for the next five days by coordinates.
     *
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return The {@link ForecastResponse} containing the weather forecast information.
     */
    public ForecastResponse getWeatherFiveDaysForecastsByCoordinates(double lat, double lon) {
        latitudeRangeCheck(lat, 90, "Latitude must be between (-90,90) ");
        latitudeRangeCheck(lon, 180, "Longitude must be between (-180,180) ");
        return weatherClient.getWeatherFiveDaysForecastsByCoordinates(lat, lon, unit, apiKey);
    }

    /**
     * Checks if the latitude or longitude value is within the specified range.
     *
     * @param val The latitude or longitude value to check.
     * @param x   The range value.
     * @param x1  The error message to be thrown if the value is out of range.
     */
    private static void latitudeRangeCheck(double val, int x, String x1) {
        if (val > x || val < -x) {
            throw new OpenWeatherClientException(() -> x1);
        }
    }

}
