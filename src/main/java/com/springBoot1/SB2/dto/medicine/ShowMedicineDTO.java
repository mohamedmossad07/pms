package com.springBoot1.SB2.dto.medicine;

import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import com.springBoot1.SB2.dto.category.ShowIdAndNameCategoryDTO;
import com.springBoot1.SB2.dto.supplier.ShowIdAndNameSupplierDTO;
import com.springBoot1.SB2.entity.MedicineType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowMedicineDTO extends BaseTrashableAuditingDTO {
    private String scientificName;
    private String marketName;
    private ShowIdAndNameCategoryDTO category;
    private String description;
    private Double price;
    private MedicineType type;
    private ShowIdAndNameSupplierDTO supplier;
    private LocalDate expiration;
    private String img;
    private Integer count;
}

