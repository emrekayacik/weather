package com.emrekayacik.weather.service.client;

import com.emrekayacik.weather.base.exception.custom.OpenWeatherClientException;
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
        latitudeRangeCheck(lat, 90, "Latitude must be between (-90,90) ");
        latitudeRangeCheck(lon, 180, "Longitude must be between (-180,180) ");
        return weatherClient.getCurrentWeatherByCoordinates(lat,lon,unit,apiKey);
    }

    private static void latitudeRangeCheck(double val, int x, String x1) {
        if (val > x || val < -x) {
            throw new OpenWeatherClientException(() -> x1);
        }
    }

    public ForecastResponse getWeatherFiveDaysForecastsByCityName(String city) {
        return weatherClient.getWeatherFiveDaysForecastsByCityName(city, unit, apiKey);
    }
    public ForecastResponse getWeatherFiveDaysForecastsByCoordinates(double lat, double lon) {
        latitudeRangeCheck(lat, 90, "Latitude must be between (-90,90) ");
        latitudeRangeCheck(lon, 180, "Longitude must be between (-180,180) ");
        return weatherClient.getWeatherFiveDaysForecastsByCoordinates(lat,lon,unit,apiKey);
    }
}
