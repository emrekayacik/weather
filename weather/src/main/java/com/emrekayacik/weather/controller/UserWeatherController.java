package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.base.response.RestResponse;
import com.emrekayacik.weather.dto.UserWeatherDto;
import com.emrekayacik.weather.request.UserSaveCityByNameRequest;
import com.emrekayacik.weather.response.UserSaveCityByNameResponse;
import com.emrekayacik.weather.service.userWeather.UserWeatherServiceContract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/userWeathers")
@RequiredArgsConstructor
@Validated
@SecurityRequirement(name = "bearerAuth")
public class UserWeatherController {

    private final UserWeatherServiceContract service;

    @GetMapping
    @Operation(summary = "Get all UserWeather records")
    public ResponseEntity<RestResponse<List<UserWeatherDto>>> get() {
        return ResponseEntity.ok(RestResponse.of(service.findAll()));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get UserWeather by id")
    public ResponseEntity<RestResponse<UserWeatherDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(service.findById(id)));
    }
    @GetMapping("/user")
    @Operation(summary = "Get user saved cities")
    public ResponseEntity<RestResponse<List<UserWeatherDto>>> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(RestResponse.of(service.findByUsername(username)));
    }
    @GetMapping("/city")
    @Operation(summary = "Get by city name")
    public ResponseEntity<RestResponse<List<UserWeatherDto>>> findByCityName(@RequestParam String cityName) {
        return ResponseEntity.ok(RestResponse.of(service.findByCityName(cityName)));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user-weather record")
    public ResponseEntity<RestResponse<Object>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }
    @PostMapping
    @Operation(summary = "User saves a city by its name")
    public ResponseEntity<RestResponse<UserSaveCityByNameResponse>> saveCityByName(@Valid @RequestBody UserSaveCityByNameRequest request) {
        return ResponseEntity.ok(RestResponse.of(service.save(request)));
    }
    @GetMapping("/users/own")
    @Operation(summary = "User current user's saved cities")
    public ResponseEntity<RestResponse<List<UserWeatherDto>>> getCurrentUserCities() {
        return ResponseEntity.ok(RestResponse.of(service.findCurrentUserCities()));
    }
}
