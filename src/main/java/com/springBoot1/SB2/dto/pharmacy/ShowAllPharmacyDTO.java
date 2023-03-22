package com.springBoot1.SB2.dto.pharmacy;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import com.springBoot1.SB2.dto.user.ShowIdNameUserDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllPharmacyDTO extends IdAndCreatedAtAuditingDTO {
    private String name;
    private String address;
    private ShowIdNameUserDTO manager;
}
