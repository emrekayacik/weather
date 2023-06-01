package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.base.response.RestResponse;
import com.emrekayacik.weather.security.request.AuthenticationRequest;
import com.emrekayacik.weather.security.request.RegisterRequest;
import com.emrekayacik.weather.security.response.AuthenticationResponse;
import com.emrekayacik.weather.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins="*")
public class AuthenticationController {
    private final AuthenticationService authService;

    /**
     * Endpoint for user registration.
     *
     * @param request The registration request containing user details.
     * @return ResponseEntity containing the registration response.
     */
    @PostMapping("/register")
    public ResponseEntity<RestResponse<AuthenticationResponse>> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(RestResponse.of(authService.register(request)));
    }

    /**
     * Endpoint for user authentication.
     *
     * @param request The authentication request containing user credentials.
     * @return ResponseEntity containing the authentication response.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<RestResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(RestResponse.of(authService.authenticate(request)));
    }
}