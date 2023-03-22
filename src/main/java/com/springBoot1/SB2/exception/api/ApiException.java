package com.springBoot1.SB2.exception.api;

import org.springframework.http.HttpStatus;

public class ApiException extends ApiRequestException {

    public ApiException( String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
