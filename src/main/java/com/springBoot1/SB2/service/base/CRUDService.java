package com.springBoot1.SB2.service.base;

import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;

import java.util.List;

public interface CRUDService<ID, CreateDTO, ViewAllDTO, ViewDTO, UpdateDTO> {
    void create(CreateDTO createDTO) throws  UnAuthorizedAccessException, ApiException;

    void update(ID id, UpdateDTO updateDTO) throws  UnAuthorizedAccessException, ApiException;

    ViewDTO show(ID id) throws  UnAuthorizedAccessException, ApiException;

    void delete(ID id) throws  UnAuthorizedAccessException, ApiException;

    List<ViewAllDTO> findAll(int page, int size, String sort);
}
