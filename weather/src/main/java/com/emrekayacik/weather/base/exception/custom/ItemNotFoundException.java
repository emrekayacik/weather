package com.emrekayacik.weather.base.exception.custom;

import com.emrekayacik.weather.base.exception.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * An exception class representing an item not found error.
 * It is a specific type of BusinessException and is used when a requested item cannot be found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends BusinessException{

    public ItemNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}