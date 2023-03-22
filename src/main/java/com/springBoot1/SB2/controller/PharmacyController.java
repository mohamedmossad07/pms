package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.pharmacy.CreatePharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowAllPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.UpdatePharmacyDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${API.URL_PREFIX}pharmacies")
public class PharmacyController extends CURDTrashableController<Long, CreatePharmacyDTO, ShowAllPharmacyDTO, ShowPharmacyDTO, UpdatePharmacyDTO> {
}
