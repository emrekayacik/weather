package com.emrekayacik.weather.service.client;

import com.emrekayacik.weather.client.openweather.client.WeatherClient;
import com.emrekayacik.weather.client.openweather.response.current.CurrentWeatherResponse;
import com.emrekayacik.weather.client.openweather.response.forecast.ForecastResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientService {
    private final WeatherClient weatherClient;
    @Value("${openweathermap.apiKey}")
    String apiKey;
    @Value("${openweathermap.unit}")
    String unit;
    public CurrentWeatherResponse getCurrentWeatherByCityName(String city) {
        return weatherClient.getCurrentWeatherByCityName(city,unit,apiKey);
    }
    public CurrentWeatherResponse getCurrentWeatherByCoordinates(double lat,double lon) {
        return weatherClient.getCurrentWeatherByCoordinates(lat,lon,unit,apiKey);
    }
    public ForecastResponse getWeatherFiveDaysForecastsByCityName(String city) {
        ForecastResponse response = weatherClient.getWeatherFiveDaysForecastsByCityName(city, unit, apiKey);
        return response;
    }
    public ForecastResponse getWeatherFiveDaysForecastsByCoordinates(double lat, double lon) {
        return weatherClient.getWeatherFiveDaysForecastsByCoordinates(lat,lon,unit,apiKey);
    }
}
