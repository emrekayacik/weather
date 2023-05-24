package com.emrekayacik.weather.base.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class GenericErrorMessage implements BaseErrorMessage {

    private LocalDateTime errorDate;
    private String message;
    private String detail;
}
