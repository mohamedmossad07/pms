package com.springBoot1.SB2.dto.medicine;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import com.springBoot1.SB2.dto.category.ShowIdAndNameCategoryDTO;
import com.springBoot1.SB2.entity.MedicineType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllMedicineDTO extends IdAndCreatedAtAuditingDTO {
    private String scientificName;
    private String marketName;
    private ShowIdAndNameCategoryDTO category;
    private Double price;
    private MedicineType type;
    private LocalDate expiration;
    private Integer count;
}

