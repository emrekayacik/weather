package com.emrekayacik.weather.base.exception;

import com.emrekayacik.weather.base.exception.custom.BusinessException;
import com.emrekayacik.weather.base.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


/**
 * A custom exception handler for handling exceptions thrown by the application.
 * This class provides centralized exception handling and returns appropriate error responses.
 */
@RestController
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LogManager.getLogger(CustomizedResponseEntityExceptionHandler.class);

    /**
     * Handles all exceptions and returns a ResponseEntity with an error message.
     *
     * @param e          the exception
     * @param webRequest the web request
     * @return a ResponseEntity with an error message
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest webRequest) {
        return getObjectResponseEntity(e, webRequest);
    }

    /**
     * Handles TransactionSystemException and returns a ResponseEntity with an error message.
     *
     * @param e          the exception
     * @param webRequest the web request
     * @return a ResponseEntity with an error message
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(TransactionSystemException e, WebRequest webRequest) {
        return getObjectResponseEntity(e, webRequest);
    }

    /**
     * Creates a ResponseEntity with an error message based on the given exception and web request.
     *
     * @param e          the exception
     * @param webRequest the web request
     * @return a ResponseEntity with an error message
     */
    private ResponseEntity<Object> getObjectResponseEntity(Exception e, WebRequest webRequest) {
        String message = e.getMessage();
        String description = webRequest.getDescription(false);
        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);
        var response = RestResponse.error(genericErrorMessage);
        logger.error("message: " + message + " detail: " + description);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles BusinessException and returns a ResponseEntity with an error message.
     *
     * @param e          the exception
     * @param webRequest the web request
     * @return a ResponseEntity with an error message
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(BusinessException e, WebRequest webRequest) {
        String message = e.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);
        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);
        var response = RestResponse.error(genericErrorMessage);
        logger.error("message: " + message + " detail: " + description);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
