package com.emrekayacik.weather.base.exception.custom;

import com.emrekayacik.weather.base.exception.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OpenWeatherClientException extends BusinessException {
    public OpenWeatherClientException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

}
