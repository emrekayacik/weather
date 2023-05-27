package com.emrekayacik.weather.client.openweather.response.current;

import com.emrekayacik.weather.client.openweather.domain.Coordinate;
import com.emrekayacik.weather.client.openweather.domain.WeatherCondition;
import com.emrekayacik.weather.client.openweather.domain.WeatherMain;
import com.emrekayacik.weather.client.openweather.domain.Wind;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents the response from the OpenWeatherMap API for current weather information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CurrentWeatherResponse {
    private String name;
    private Coordinate coord;
    private List<WeatherCondition> weather;
    private WeatherMain main;
    private Wind wind;
}