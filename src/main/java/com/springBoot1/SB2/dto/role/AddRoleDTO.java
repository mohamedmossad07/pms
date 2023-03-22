package com.springBoot1.SB2.dto.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
public class AddRoleDTO {
    @Size(min = 2, max = 200, message = "{roles.name.Size}")
    private String name;//role name
    @NotNull(message = "{roles.priority.NotNull}")
    @PositiveOrZero(message = "{roles.priority.PositiveOrZero")
    private Short priority;
}
