package com.emrekayacik.weather.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserSaveCityByNameRequest(@NotBlank(message = "City name cannot be blank")
                                        @Length(min = 1, max = 100, message = "City name must be 1-100 characters")
                                        String city,
                                        @NotBlank(message = "Username cannot be blank")
                                        @Length(min = 3, max = 25, message = "Username must be 3-25 characters")
                                        String username) {
}
