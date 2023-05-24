package com.emrekayacik.weather.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserLoginRequest (@NotBlank @Length(min = 3,max = 25)
                                String username,
                                @NotBlank @Length(min = 3,max = 25)
                                String password) {
}