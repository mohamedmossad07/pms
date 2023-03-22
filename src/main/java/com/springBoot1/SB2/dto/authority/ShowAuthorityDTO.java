package com.springBoot1.SB2.dto.authority;

import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import com.springBoot1.SB2.dto.role.ShowRoleIdAndNameDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAuthorityDTO extends BaseTrashableAuditingDTO {
    private String name;
    private Set<ShowRoleIdAndNameDTO> roles;
}
