package com.emrekayacik.weather.client.openweather.response;

import com.emrekayacik.weather.client.openweather.domain.WeatherMain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class WeatherResponse {

    private WeatherMain main;
}