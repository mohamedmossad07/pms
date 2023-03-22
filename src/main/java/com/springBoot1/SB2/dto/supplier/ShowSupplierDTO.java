package com.springBoot1.SB2.dto.supplier;

import com.springBoot1.SB2.dto.address.ShowIdAndAddressDTO;
import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowSupplierDTO extends BaseTrashableAuditingDTO {
    private String name;
    private String email;
    private ShowIdAndAddressDTO address = new ShowIdAndAddressDTO();
    private Set<String> phones;

}
