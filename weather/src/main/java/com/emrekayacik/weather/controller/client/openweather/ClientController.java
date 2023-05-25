package com.emrekayacik.weather.controller.client.openweather;

import com.emrekayacik.weather.client.openweather.client.WeatherClient;
import com.emrekayacik.weather.client.openweather.response.WeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("weather")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final WeatherClient weatherClient;
    @Value("${openweathermap.apiKey}")
    String apiKey;
    @GetMapping("/city")
    @Operation(summary = "Get Current Weather Info By City Name")
    public String getWeather(@RequestParam("city") String city) {

        WeatherResponse response = weatherClient.getWeather(city, apiKey );
        double temperature = response.getMain().getTemp();
        return "Temperature in " + city + ": " + temperature + "°C";
    }
    @GetMapping("/coordinate")
    @Operation(summary = "Get Current Weather Info By Coordinate")
    public String getWeather(@RequestParam("lat") double lat,@RequestParam("lon") double lon) {

        WeatherResponse response = weatherClient.getWeatherByCoordinate(lat,lon, apiKey );
        double temperature = response.getMain().getTemp();
        return "Temperature in " + lat + "-" + lon + " "+ temperature + "°C";
    }
}