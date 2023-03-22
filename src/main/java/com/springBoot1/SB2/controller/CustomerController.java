package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.customer.CreateCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowAllCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowCustomerDTO;
import com.springBoot1.SB2.dto.customer.UpdateCustomerDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${API.URL_PREFIX}customers")
public class CustomerController extends CURDTrashableController<Long, CreateCustomerDTO, ShowAllCustomerDTO, ShowCustomerDTO, UpdateCustomerDTO> {
}
