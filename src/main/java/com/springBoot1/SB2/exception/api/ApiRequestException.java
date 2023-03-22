package com.springBoot1.SB2.exception.api;

import org.springframework.http.HttpStatus;

public abstract class ApiRequestException extends Exception {
    private final HttpStatus httpStatus;

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public Throwable getThrowable() {
        return this;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
