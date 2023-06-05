package com.emrekayacik.weather.controller.client.openweather;

import com.emrekayacik.weather.base.response.RestResponse;
import com.emrekayacik.weather.client.openweather.response.current.CurrentWeatherResponse;
import com.emrekayacik.weather.client.openweather.response.forecast.ForecastResponse;
import com.emrekayacik.weather.service.client.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/weathers")
@RequiredArgsConstructor
@Validated
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/current/city")
    @Operation(summary = "Get Current Weather Info By City Name")
    public ResponseEntity<RestResponse<CurrentWeatherResponse>> getCurrentWeatherByCityName(@Valid @Length(min = 1,max = 100,message = "City name length must be between 1-100") @RequestParam("city") String city) {
        CurrentWeatherResponse response = clientService.getCurrentWeatherByCityName(city);
        return ResponseEntity.ok(RestResponse.of(response));
    }
    @GetMapping("/current/coordinates")
    @Operation(summary = "Get Current Weather Info By Coordinate")
    public ResponseEntity<RestResponse<CurrentWeatherResponse>> getCurrentWeatherByCoordinates(@Valid @NotNull(message = "Latitude cannot be blank.") @RequestParam("lat") double lat,@Valid @NotNull(message = "Longitude cannot be blank.") @RequestParam("lon") double lon) {
        CurrentWeatherResponse response = clientService.getCurrentWeatherByCoordinates(lat,lon);

        return ResponseEntity.ok(RestResponse.of(response));
    }

    @GetMapping("/forecasts/city")
    @Operation(summary = "Get Weather Forecasts By City Name (5 Days 3-hours each")
    public ResponseEntity<RestResponse<ForecastResponse>> getWeatherFiveDaysForecastsByCityName(@Valid @Length(min = 1,max = 100,message = "City name length must be between 1-100") @RequestParam("city") String city) {
        ForecastResponse response = clientService.getWeatherFiveDaysForecastsByCityName(city);
        return ResponseEntity.ok(RestResponse.of(response));
    }
    @GetMapping("/forecasts/coordinates")
    @Operation(summary = "Get Weather Forecasts By Coordinate (5 Days 3-hours each)")
    public ResponseEntity<RestResponse<ForecastResponse>> getWeatherFiveDaysForecastsByCoordinates(@Valid @NotNull(message = "Latitude cannot be blank.")@RequestParam("lat") double lat,@Valid @NotNull(message = "Longitude cannot be blank.")@RequestParam("lon") double lon) {
        ForecastResponse response = clientService.getWeatherFiveDaysForecastsByCoordinates(lat,lon);
        return ResponseEntity.ok(RestResponse.of(response));
    }
}