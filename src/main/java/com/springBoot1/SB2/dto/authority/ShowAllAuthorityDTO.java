package com.springBoot1.SB2.dto.authority;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllAuthorityDTO extends IdAndCreatedAtAuditingDTO {
    private String name;
}
