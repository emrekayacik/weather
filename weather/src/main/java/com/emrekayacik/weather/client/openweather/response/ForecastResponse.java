package com.emrekayacik.weather.client.openweather.response;

import com.emrekayacik.weather.client.openweather.domain.ForecastCity;
import com.emrekayacik.weather.client.openweather.domain.WeatherMain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ForecastResponse {
    private ForecastCity city;
    private ArrayList<WeatherResponse> list;
}
