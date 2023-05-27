package com.emrekayacik.weather.base.exception;

/**
 * An interface representing the base error message.
 * Classes implementing this interface should provide a method to retrieve the error message.
 */
public interface BaseErrorMessage {
    String getMessage();
}