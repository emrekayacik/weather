package com.emrekayacik.weather.security.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "Username cannot be blank.")
    private String username;
    @NotBlank(message = "Password cannot be blank.")
    private String password;
}
