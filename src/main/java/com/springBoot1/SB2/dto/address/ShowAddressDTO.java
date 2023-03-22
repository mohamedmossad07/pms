package com.springBoot1.SB2.dto.address;

import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAddressDTO extends BaseTrashableAuditingDTO {
    private String governorate;
    private String city;
    private String town;
    private String street;
}
