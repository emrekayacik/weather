package com.emrekayacik.weather.client.openweather.client;

import com.emrekayacik.weather.client.openweather.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherClient", url = "https://api.openweathermap.org/data/2.5/")
@Qualifier("openWeatherMapClient")
public interface WeatherClient {
    @GetMapping("weather")
    WeatherResponse getWeather(@RequestParam("q") String city, @RequestParam("appid") String apiKey);
    @GetMapping("weather")
    WeatherResponse getWeatherByCoordinate(@RequestParam("lat") double lat, @RequestParam("lon") double lon ,@RequestParam("appid") String apiKey);
}