package com.emrekayacik.weather.security.service;

import com.emrekayacik.weather.base.entity.BaseAuditableEntity;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.repository.UserRepository;
import com.emrekayacik.weather.security.enums.Role;
import com.emrekayacik.weather.security.request.AuthenticationRequest;
import com.emrekayacik.weather.security.request.RegisterRequest;
import com.emrekayacik.weather.security.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service class that handles user authentication and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user with the provided registration request.
     *
     * @param request the registration request
     * @return the authentication response containing the JWT token
     */
    public AuthenticationResponse register(RegisterRequest request) {
        // Create a new User entity with the provided registration details
        var user = User.builder()
                .username(request.getUsername())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(Role.USER)
                .build();

        // Create a new BaseAuditableEntity with the current timestamp and set it on the user
        BaseAuditableEntity baseAuditableEntity = new BaseAuditableEntity();
        baseAuditableEntity.setCreatedDate(LocalDateTime.now());
        user.setAuditable(baseAuditableEntity);

        // Save the user entity in the repository
        repository.save(user);

        // Generate a JWT token for the user
        var jwtToken = jwtService.generateToken(user);

        // Return the authentication response with the JWT token
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticates a user with the provided authentication request.
     *
     * @param request the authentication request
     * @return the authentication response containing the JWT token
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Use the authentication manager to authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Retrieve the authenticated user from the repository
        var user = repository.findUserByUsername(request.getUsername())
                .orElseThrow();

        // Generate a JWT token for the user
        var jwtToken = jwtService.generateToken(user);

        // Return the authentication response with the JWT token
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
