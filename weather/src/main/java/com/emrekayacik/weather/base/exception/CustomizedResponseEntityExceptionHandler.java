package com.emrekayacik.weather.base.exception;

import com.emrekayacik.weather.base.exception.custom.BusinessException;
import com.emrekayacik.weather.base.response.RestResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;


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

    /**
     * Handles DataIntegrityViolationException.
     *
     * @param e          the exception
     * @param webRequest the web request
     * @return a ResponseEntity with an error message
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(DataIntegrityViolationException e, WebRequest webRequest) {
        String message = e.getCause().getMessage().split("\n ")[1].split("]")[0].substring(13);
        String description = webRequest.getDescription(false);
        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);
        var response = RestResponse.error(genericErrorMessage);
        logger.error("message: " + message + " detail: " + description);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String finalMsg = errors.entrySet()
                .stream()
                .map(Object::toString)
                .collect(joining(",\n"));
        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), finalMsg, finalMsg);
        var response = RestResponse.error(genericErrorMessage);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles FeignException.
     *
     * @param e          the exception
     * @param webRequest the web request
     * @return a ResponseEntity with an error message
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(FeignException e, WebRequest webRequest) {
        String message = "Requested city cannot found. Please correct your input.";
        String description = e.getMessage() + " " +webRequest.getDescription(false);
        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);
        var response = RestResponse.error(genericErrorMessage);
        logger.error("message: " + message + " detail: " + description);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
