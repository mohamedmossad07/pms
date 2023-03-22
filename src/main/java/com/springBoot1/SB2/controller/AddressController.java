package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.address.CreateAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAllAddressDTO;
import com.springBoot1.SB2.dto.address.UpdateAddressDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${API.URL_PREFIX}addresses")
public class AddressController extends CURDTrashableController<Long, CreateAddressDTO, ShowAllAddressDTO, ShowAddressDTO, UpdateAddressDTO> {
}
