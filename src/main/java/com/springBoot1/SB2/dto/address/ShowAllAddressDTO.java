package com.springBoot1.SB2.dto.address;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllAddressDTO extends IdAndCreatedAtAuditingDTO {
    private String governorate;
    private String city;
    private String town;
    private String street;
}
