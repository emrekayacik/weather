package com.emrekayacik.weather.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserChangeEmailRequest(@NotBlank String username, @NotBlank @Email String email) {
}
