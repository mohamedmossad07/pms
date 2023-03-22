package com.springBoot1.SB2.exception.api;

import org.springframework.http.HttpStatus;

public class CouldntCreateUploadRootPathException extends ApiRequestException {
    public CouldntCreateUploadRootPathException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
