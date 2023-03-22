package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.authority.AddOrUpdateAuthorityDTO;
import com.springBoot1.SB2.dto.authority.ShowAllAuthorityDTO;
import com.springBoot1.SB2.dto.authority.ShowAuthorityDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${API.URL_PREFIX}authorities")
public class AuthorityController extends CURDTrashableController<Long, AddOrUpdateAuthorityDTO, ShowAllAuthorityDTO, ShowAuthorityDTO, AddOrUpdateAuthorityDTO> {
}
