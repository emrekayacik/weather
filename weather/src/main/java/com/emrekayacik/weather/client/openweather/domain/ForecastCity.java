package com.emrekayacik.weather.client.openweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the city information in the weather forecast response by OpenWeatherApi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ForecastCity {
    private String name;
    private String country;
    private Coordinate coord;
}