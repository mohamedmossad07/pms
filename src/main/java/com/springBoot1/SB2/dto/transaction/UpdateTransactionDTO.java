package com.springBoot1.SB2.dto.transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString
public class UpdateTransactionDTO {
    private Long customer;
    private Set<CreateTransactionalMedicineDTO> medicines;
    private Long pharmacy;
}
