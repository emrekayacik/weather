package com.emrekayacik.weather.client.openweather.response.forecast;

import com.emrekayacik.weather.client.openweather.domain.WeatherCondition;
import com.emrekayacik.weather.client.openweather.domain.WeatherMain;
import com.emrekayacik.weather.client.openweather.domain.Wind;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents the inner response from the OpenWeatherMap API for weather forecasts.
 * Used in ForecastResponse
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ForecastWeatherResponse {
    private Long dt;

    private WeatherMain main;
    private List<WeatherCondition> weather;
    private Wind wind;
    private String dt_txt;
}