package com.springBoot1.SB2.service.base;

public interface TrashableCRUDService<ID, CreateDTO, ViewAllDTO, ViewDTO, UpdateDTO> extends CRUDService<ID, CreateDTO, ViewAllDTO, ViewDTO, UpdateDTO>, TrashableService<ID> {
}
