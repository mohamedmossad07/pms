package com.springBoot1.SB2.dto.transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreateTransactionalMedicineDTO {
    @NotNull(message = "{transactions.medicines.NotNull}")
    private Long medicine;
    @NotNull(message = "{transactions.count.NotNull}")
    private Integer count;
}
