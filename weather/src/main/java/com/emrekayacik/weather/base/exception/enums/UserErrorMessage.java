package com.emrekayacik.weather.base.exception.enums;

import com.emrekayacik.weather.base.exception.BaseErrorMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserErrorMessage implements BaseErrorMessage {
    USER_NOT_FOUND("User not found");

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
