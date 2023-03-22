package com.springBoot1.SB2.dto.transaction;

import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import com.springBoot1.SB2.dto.customer.ShowIdAndCustomerDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowIdPharmacyDTO;
import lombok.*;

import java.util.Set;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShowTransactionDTO extends BaseTrashableAuditingDTO {
    private ShowIdAndCustomerDTO customer;
    private Set<ShowTransactionalMedicineDTO> medicines;
    private Double price;
    private ShowIdPharmacyDTO pharmacy;
    private String invoice;
}
