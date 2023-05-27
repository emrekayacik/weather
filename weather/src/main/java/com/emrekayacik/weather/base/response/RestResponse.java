package com.emrekayacik.weather.base.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a restful custom response object.
 *
 * @param <T> the type of the data object included in the response
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> implements Serializable {

    private T data;
    private LocalDateTime responseDate;
    private boolean isSuccess;
    private String messages;

    /**
     * Constructs a RestResponse object with the specified data and success status.
     *
     * @param data       the data object to be included in the response
     * @param isSuccess  the success status of the response
     */
    public RestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.responseDate = LocalDateTime.now();
    }

    /**
     * Creates a RestResponse object with the specified data and sets the success status to true.
     *
     * @param t  the data object to be included in the response
     * @return a RestResponse object with the specified data and success status
     */
    public static <T> RestResponse<T> of(T t){
        return new RestResponse<>(t, true);
    }

    /**
     * Creates an empty RestResponse object with a null data and sets the success status to true.
     *
     * @return an empty RestResponse object
     */
    public static <T> RestResponse<T> empty(){
        return new RestResponse<>(null, true);
    }

    /**
     * Creates a RestResponse object with the specified data and sets the success status to false.
     *
     * @param t  the data object to be included in the response
     * @return a RestResponse object with the specified data and error status
     */
    public static <T> RestResponse<T> error(T t){
        return new RestResponse<>(t, false);
    }

    /**
     * Sets the messages associated with the response.
     *
     * @param messages  the messages to be set
     */
    public void setMessages(String messages) {
        this.messages = messages;
    }
}
