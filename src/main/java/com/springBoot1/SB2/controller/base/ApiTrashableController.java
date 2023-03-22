package com.springBoot1.SB2.controller.base;

import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import org.springframework.http.ResponseEntity;

public interface ApiTrashableController<ID> {
    ResponseEntity<?> trash(ID id) throws  UnAuthorizedAccessException, ApiException;

    ResponseEntity<?> restore(ID id) throws  UnAuthorizedAccessException, ApiException;
}
