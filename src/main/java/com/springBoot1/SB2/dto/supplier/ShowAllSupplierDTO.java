package com.springBoot1.SB2.dto.supplier;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllSupplierDTO extends IdAndCreatedAtAuditingDTO {
    private String name;
    private String email;
    private String address;
}
