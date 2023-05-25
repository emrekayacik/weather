package com.emrekayacik.weather.dto;

import com.emrekayacik.weather.base.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWeatherDto extends BaseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String cityName;

    private UserDto user;
}
