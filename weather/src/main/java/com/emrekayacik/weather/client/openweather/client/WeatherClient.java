package com.emrekayacik.weather.client.openweather.client;

import com.emrekayacik.weather.client.openweather.response.current.CurrentWeatherResponse;
import com.emrekayacik.weather.client.openweather.response.forecast.ForecastResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client interface for interacting with the OpenWeatherMap API.
 */
@FeignClient(name = "weatherClient", url = "https://api.openweathermap.org/data/2.5/")
@Qualifier("openWeatherMapClient")
@Repository
public interface WeatherClient {
    /**
     * Retrieves the current weather information by city name.
     *
     * @param city   The name of the city.
     * @param units  The units of temperature measurement.
     * @param apiKey The API key for authentication.
     * @return The CurrentWeatherResponse object containing the current weather information.
     */
    @GetMapping("weather")
    CurrentWeatherResponse getCurrentWeatherByCityName(@RequestParam("q") String city,
                                                       @RequestParam("units") String units,
                                                       @RequestParam("appid") String apiKey);

    /**
     * Retrieves the current weather information by coordinates (latitude and longitude).
     *
     * @param lat    The latitude coordinate.
     * @param lon    The longitude coordinate.
     * @param units  The units of temperature measurement.
     * @param apiKey The API key for authentication.
     * @return The CurrentWeatherResponse object containing the current weather information.
     */
    @GetMapping("weather")
    CurrentWeatherResponse getCurrentWeatherByCoordinates(@RequestParam("lat") double lat,
                                                          @RequestParam("lon") double lon,
                                                          @RequestParam("units") String units,
                                                          @RequestParam("appid") String apiKey);

    /**
     * Retrieves the weather forecasts for the next 5 days with 3-hours step by city name.
     *
     * @param city   The name of the city.
     * @param units  The units of temperature measurement.
     * @param apiKey The API key for authentication.
     * @return The ForecastResponse object containing the weather forecasts.
     */
    @GetMapping("forecast")
    ForecastResponse getWeatherFiveDaysForecastsByCityName(@RequestParam("q") String city,
                                                           @RequestParam("units") String units,
                                                           @RequestParam("appid") String apiKey);

    /**
     * Retrieves the weather forecasts for the next 5 days with 3-hours step by coordinates (latitude and longitude).
     *
     * @param lat    The latitude coordinate.
     * @param lon    The longitude coordinate.
     * @param units  The units of temperature measurement.
     * @param apiKey The API key for authentication.
     * @return The ForecastResponse object containing the weather forecasts.
     */
    @GetMapping("forecast")
    ForecastResponse getWeatherFiveDaysForecastsByCoordinates(@RequestParam("lat") double lat,
                                                              @RequestParam("lon") double lon,
                                                              @RequestParam("units") String units,
                                                              @RequestParam("appid") String apiKey);
}
