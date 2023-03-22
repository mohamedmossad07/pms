package com.springBoot1.SB2.dto.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UpdateRoleDTO {
    private String name;//role name
    private Short priority;
}
