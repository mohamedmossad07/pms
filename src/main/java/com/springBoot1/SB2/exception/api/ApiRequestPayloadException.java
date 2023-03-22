package com.springBoot1.SB2.exception.api;

import org.springframework.http.HttpStatus;

public class ApiRequestPayloadException extends ApiRequestException {
    private String field;

    public ApiRequestPayloadException(String field, String message) {
        super(message, HttpStatus.BAD_REQUEST);
        setField(field);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
