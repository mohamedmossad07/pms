package com.springBoot1.SB2.dto.medicine;

import com.springBoot1.SB2.dto.category.ShowIdAndNameCategoryDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowIdMedicineDTO {
    private Long id;
    private String scientificName;
    private String marketName;
    private ShowIdAndNameCategoryDTO category;
    private Double price;
}

