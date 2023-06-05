package com.emrekayacik.weather.base.exception.custom;

import com.emrekayacik.weather.base.exception.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception class representing the OpenWeatherClientException.
 * It extends the BusinessException class and is used to handle business-related errors.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OpenWeatherClientException extends BusinessException {
    public OpenWeatherClientException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

}
