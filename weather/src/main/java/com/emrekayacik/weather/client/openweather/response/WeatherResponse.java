package com.emrekayacik.weather.client.openweather.response;

import com.emrekayacik.weather.client.openweather.domain.WeatherMain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private WeatherMain main;

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }
}