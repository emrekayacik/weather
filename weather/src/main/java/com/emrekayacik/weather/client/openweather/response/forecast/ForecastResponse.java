package com.emrekayacik.weather.client.openweather.response.forecast;

import com.emrekayacik.weather.client.openweather.domain.ForecastCity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Represents the top response from the OpenWeatherMap API for weather forecasts.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ForecastResponse {
    private ForecastCity city;
    private List<ForecastWeatherResponse> list;
    private String cod;
    private String message;
}
