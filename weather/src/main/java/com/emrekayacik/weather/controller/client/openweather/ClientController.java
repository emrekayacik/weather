package com.emrekayacik.weather.controller.client.openweather;

import com.emrekayacik.weather.client.openweather.client.WeatherClient;
import com.emrekayacik.weather.client.openweather.response.ForecastResponse;
import com.emrekayacik.weather.client.openweather.response.WeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("weather")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final WeatherClient weatherClient;
    @Value("${openweathermap.apiKey}")
    String apiKey;
    @GetMapping("/current/city")
    @Operation(summary = "Get Current Weather Info By City Name")
    public String getCurrentWeatherByCityName(@RequestParam("city") String city) {

        WeatherResponse response = weatherClient.getCurrentWeatherByCityName(city, apiKey );
        double temperature = response.getMain().getTemp();
        return "Temperature in " + city + ": " + temperature + "°C";
    }
    @GetMapping("/current/coordinates")
    @Operation(summary = "Get Current Weather Info By Coordinate")
    public String getCurrentWeatherByCoordinates(@RequestParam("lat") double lat,@RequestParam("lon") double lon) {

        WeatherResponse response = weatherClient.getCurrentWeatherByCoordinates(lat,lon, apiKey );
        double temperature = response.getMain().getTemp();
        return "Temperature in " + lat + "-" + lon + " "+ temperature + "°C";
    }

    @GetMapping("/forecasts/city")
    @Operation(summary = "Get Weather Forecasts By City Name (5 Days 3-hours each")
    public String getWeatherFiveDaysForecastsByCityName(@RequestParam("city") String city) {

        ForecastResponse response = weatherClient.getWeatherFiveDaysForecastsByCityName(city, apiKey );
        Date sunrise = new Date(response.getCity().getSunrise());
        String country = response.getCity().getCountry();
        return "country: " + country;
    }
    @GetMapping("/forecasts/coordinates")
    @Operation(summary = "Get Weather Forecasts By Coordinate (5 Days 3-hours each)")
    public String getWeatherFiveDaysForecastsByCoordinates(@RequestParam("lat") double lat,@RequestParam("lon") double lon) {

        ForecastResponse response = weatherClient.getWeatherFiveDaysForecastsByCoordinates(lat,lon, apiKey );
        String country = response.getCity().getCountry();
        return "country: " + country;
    }
}