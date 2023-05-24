package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.base.response.RestResponse;
import com.emrekayacik.weather.security.request.AuthenticationRequest;
import com.emrekayacik.weather.security.request.RegisterRequest;
import com.emrekayacik.weather.security.response.AuthenticationResponse;
import com.emrekayacik.weather.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    @PostMapping("/register")
    public ResponseEntity<RestResponse<AuthenticationResponse>> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(RestResponse.of(authService.register(request)));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<RestResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(RestResponse.of(authService.authenticate(request)));
    }
}
