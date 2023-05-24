package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.client.openweather.client.WeatherClient;
import com.emrekayacik.weather.client.openweather.response.WeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("weather")
@SecurityRequirement(name = "bearerAuth")
public class WeatherController {

    private final WeatherClient weatherClient;
    @Value("${openweathermap.apiKey}")
    String apiKey;
    @GetMapping
    @Operation(summary = "Get Current Weather Info By City Name")
    public String getWeather(@RequestParam("city") String city) {

        WeatherResponse response = weatherClient.getWeather(city, apiKey );
        double temperature = response.getMain().getTemp();
        return "Temperature in " + city + ": " + temperature + "°C";
    }
}