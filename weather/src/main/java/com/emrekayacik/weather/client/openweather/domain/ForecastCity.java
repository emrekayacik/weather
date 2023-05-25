package com.emrekayacik.weather.client.openweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ForecastCity {
    private String name;
    private String country;
    private Long sunrise;
    private Long sunset;
}
