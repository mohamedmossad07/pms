package com.springBoot1.SB2.dto.role;

import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import com.springBoot1.SB2.dto.user.ShowIdNameUserDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowRoleDTO extends BaseTrashableAuditingDTO {
    private String name;//role name
    private Set<ShowIdNameUserDTO> users;
    private Short priority;
}
