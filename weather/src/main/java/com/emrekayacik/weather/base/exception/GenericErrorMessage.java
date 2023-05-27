package com.emrekayacik.weather.base.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * A generic error message class representing an error response.
 */
@Data
@AllArgsConstructor
@Builder
public class GenericErrorMessage implements BaseErrorMessage {

    /**
     * The date and time when the error occurred.
     */
    private LocalDateTime errorDate;

    /**
     * The error message.
     */
    private String message;

    /**
     * Additional details or description of the error.
     */
    private String detail;
}
