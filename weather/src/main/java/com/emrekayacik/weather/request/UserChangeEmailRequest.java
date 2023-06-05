package com.emrekayacik.weather.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserChangeEmailRequest(@NotBlank(message = "Username cannot be blank.")
                                     String username,
                                     @NotBlank(message = "Email cannot be blank.")
                                     @Email(message = "Email format is not correct. Please try the pattern <youremail@domain.com>")
                                     String email) {
}
