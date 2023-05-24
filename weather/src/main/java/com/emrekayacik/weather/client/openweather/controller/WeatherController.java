package com.emrekayacik.weather.client.openweather.controller;

import com.emrekayacik.weather.client.openweather.client.WeatherClient;
import com.emrekayacik.weather.client.openweather.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("weather")
public class WeatherController {

    private final WeatherClient weatherClient;
    @Value("${openweathermap.apiKey}")
    String apiKey;
    @GetMapping
    public String getWeather(@RequestParam("city") String city) {

        WeatherResponse response = weatherClient.getWeather(city, apiKey );
        double temperature = response.getMain().getTemp();
        return "Temperature in " + city + ": " + temperature + "°C";
    }
}