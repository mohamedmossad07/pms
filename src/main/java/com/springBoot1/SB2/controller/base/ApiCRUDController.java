package com.springBoot1.SB2.controller.base;

import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import org.springframework.http.ResponseEntity;

public interface ApiCRUDController<ID, CreateDTO, ViewDTO, UpdateDTO> {
    ResponseEntity<?> create(CreateDTO createDTO) throws  UnAuthorizedAccessException, ApiException;

    ResponseEntity<?> show(ID id) throws  UnAuthorizedAccessException, ApiException;

    ResponseEntity<?> update(ID id, UpdateDTO updateDTO) throws  UnAuthorizedAccessException, ApiException;

    ResponseEntity<?> delete(ID id) throws  UnAuthorizedAccessException, ApiException;
}
