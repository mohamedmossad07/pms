package com.springBoot1.SB2.dto.pharmacy;

import com.springBoot1.SB2.dto.address.ShowIdAndAddressDTO;
import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import com.springBoot1.SB2.dto.user.ShowIdNameUserDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowPharmacyDTO extends BaseTrashableAuditingDTO {
    private String name;
    private ShowIdAndAddressDTO address = new ShowIdAndAddressDTO();
    private ShowIdNameUserDTO manager;
    private Set<ShowIdNameUserDTO> users;

}
