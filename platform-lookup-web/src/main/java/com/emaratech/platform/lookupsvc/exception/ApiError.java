package com.emaratech.platform.lookupsvc.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ApiError class is used to represent an Http Error response.
 */

public class ApiError {
    /**
     * the http status value
     */
    private int status;

    /**
     * time when error occurred.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * brief message about error
     */
    private String message;

    /**
     * error detail
     */
    private String error;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * The one argument constructor.
     *
     * @param status the http status
     */
    public ApiError(HttpStatus status) {
        this();
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}