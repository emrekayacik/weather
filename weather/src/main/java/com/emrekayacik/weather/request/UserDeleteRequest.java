package com.emrekayacik.weather.request;

import jakarta.validation.constraints.NotBlank;

public record UserDeleteRequest(@NotBlank String username) {
}
