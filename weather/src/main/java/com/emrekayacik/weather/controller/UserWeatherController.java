package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.base.response.RestResponse;
import com.emrekayacik.weather.request.UserSaveCityByNameRequest;
import com.emrekayacik.weather.response.UserSaveCityByNameResponse;
import com.emrekayacik.weather.service.userWeather.UserWeatherServiceContract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-weather")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserWeatherController {

    private final UserWeatherServiceContract service;

    @PostMapping("/")
    @Operation(summary = "User saves a city by name")
    public ResponseEntity<RestResponse<UserSaveCityByNameResponse>> saveCityByName(@RequestBody UserSaveCityByNameRequest request) {
        return ResponseEntity.ok(RestResponse.of(service.save(request)));
    }
}
