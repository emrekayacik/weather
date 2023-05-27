package com.emrekayacik.weather.client.openweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the main weather information in the weather response by OpenWeatherApi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class WeatherMain {
    private double temp;
    private double feels_like;
    private double humidity;
    private double pressure;
}