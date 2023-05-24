package com.emrekayacik.weather.base.exception;

import com.emrekayacik.weather.base.exception.custom.BusinessException;
import com.emrekayacik.weather.base.response.RestResponse;
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

@RestController
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest webRequest) {

        return getObjectResponseEntity(e, webRequest);
    }

    private ResponseEntity<Object> getObjectResponseEntity(Exception e, WebRequest webRequest) {
        String message = e.getMessage();
        String description = webRequest.getDescription(false);
        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);
        var response = RestResponse.error(genericErrorMessage);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(TransactionSystemException e, WebRequest webRequest) {

        return getObjectResponseEntity(e, webRequest);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(BusinessException e, WebRequest webRequest) {

        String message = e.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);
        var response = RestResponse.error(genericErrorMessage);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}