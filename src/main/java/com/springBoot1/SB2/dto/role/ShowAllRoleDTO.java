package com.springBoot1.SB2.dto.role;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllRoleDTO extends IdAndCreatedAtAuditingDTO {
    private String name;//role name
    private Short priority;
}
