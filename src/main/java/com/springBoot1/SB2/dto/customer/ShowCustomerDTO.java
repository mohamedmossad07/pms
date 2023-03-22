package com.springBoot1.SB2.dto.customer;

import com.springBoot1.SB2.dto.address.ShowIdAndAddressDTO;
import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import com.springBoot1.SB2.dto.transaction.ShowIdPriceTransactionDTO;
import lombok.*;

import java.util.Set;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShowCustomerDTO extends BaseTrashableAuditingDTO {
    private String fname;//first name
    private String lname;//last name
    private String email;
    private String phone;
    private ShowIdAndAddressDTO address = new ShowIdAndAddressDTO();
    private Set<ShowIdPriceTransactionDTO> transactions;
}
