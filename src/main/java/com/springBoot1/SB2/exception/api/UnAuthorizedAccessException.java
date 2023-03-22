package com.springBoot1.SB2.exception.api;

import org.springframework.http.HttpStatus;

public class UnAuthorizedAccessException extends ApiRequestException {
    public UnAuthorizedAccessException() {
        super("UNAUTHORIZED ACCESS.", HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorizedAccessException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
