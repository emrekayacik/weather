package com.emrekayacik.weather.request;

import jakarta.validation.constraints.NotBlank;

public record UserDeleteRequest(@NotBlank(message = "Username cannot be blank.") String username) {
}
