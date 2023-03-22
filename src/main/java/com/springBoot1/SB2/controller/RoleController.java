package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.role.AddRoleDTO;
import com.springBoot1.SB2.dto.role.ShowAllRoleDTO;
import com.springBoot1.SB2.dto.role.ShowRoleDTO;
import com.springBoot1.SB2.dto.role.UpdateRoleDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${API.URL_PREFIX}roles")
public class RoleController extends CURDTrashableController<Long, AddRoleDTO, ShowAllRoleDTO, ShowRoleDTO, UpdateRoleDTO> {
}
