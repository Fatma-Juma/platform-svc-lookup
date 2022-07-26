package com.emaratech.platform.lookupsvc.webapp.exception;

import static org.springframework.http.HttpStatus.*;

import java.lang.invoke.MethodHandles;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Rest Exception handler class.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String LOGGING_EXCEPTION = "Logging exception.";

    /**
     * Catches the ResponseStatusException exception.
     *
     * @param ex the ResponseStatusException exception
     * @return the response object contains ApiError class
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleSessionException(ResponseStatusException ex) {
        ApiError apiError = new ApiError(ex.getStatus());
        if (ex.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException exception = (ConstraintViolationException) ex.getCause();
            String errorMessage = new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
            apiError.setMessage(errorMessage);
        } else {
            apiError.setMessage(ex.getReason());
        }
        LOG.warn(LOGGING_EXCEPTION, ex);
        return buildResponseEntity(apiError);
    }

    /**
     * Catches the IllegalArgumentException and IllegalStateException exception.
     *
     * @param ex the RuntimeException exception
     * @return the response object contains ApiError class
     */
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleSessionException(
                                                         RuntimeException ex) {
        LOG.warn(LOGGING_EXCEPTION, ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Catches the exception to request body not null values.
     *
     * @param ex the MethodArgumentNotValidException exception
     * @param headers the http headers
     * @param status the http status
     * @param request the web request
     * @return the response object contains ApiError class
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            stringBuilder.append("[ " + error.getDefaultMessage() + " ]");
        }
        ApiError apiError = new ApiError(status);
        apiError.setMessage(stringBuilder.toString());
        LOG.warn(LOGGING_EXCEPTION, ex);
        return buildResponseEntity(apiError);
    }

    /**
     * Access denied exception handler.
     *
     * @param ex the Access Denied Exception
     * @return the response object contains ApiError class
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = FORBIDDEN)
    public final ResponseEntity<Object> handleUnauthorizedException(AccessDeniedException ex) {

        LOG.warn(LOGGING_EXCEPTION, ex);
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
        apiError.setMessage(ex.getLocalizedMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        LOG.warn(LOGGING_EXCEPTION, ex);
        String errorMessage = new ArrayList<>(ex.getConstraintViolations()).get(0).getMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(errorMessage);
        return buildResponseEntity(apiError);
    }

    /**
     * Global exception handler.
     *
     * @param ex the exception
     * @return the response object contains ApiError class
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {

        LOG.warn(LOGGING_EXCEPTION, ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getLocalizedMessage());

        return buildResponseEntity(apiError);
    }

    /**
     * Build the ResponseEntity object.
     *
     * @param apiError the apiError
     * @return the response with ApiError object
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, valueOf(apiError.getStatus()));
    }

}
