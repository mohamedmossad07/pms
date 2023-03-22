package com.springBoot1.SB2.service.base;

import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;

public interface TrashableService<ID> {
    void trash(ID id) throws  UnAuthorizedAccessException, ApiException;

    void restore(ID id) throws  UnAuthorizedAccessException, ApiException;
}
