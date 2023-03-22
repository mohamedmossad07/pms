package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.supplier.CreateSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowAllSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowSupplierDTO;
import com.springBoot1.SB2.dto.supplier.UpdateSupplierDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${API.URL_PREFIX}suppliers")
public class SupplierController extends CURDTrashableController<Long, CreateSupplierDTO, ShowAllSupplierDTO, ShowSupplierDTO, UpdateSupplierDTO> {
}
