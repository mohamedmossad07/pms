package com.springBoot1.SB2.dto.medicine;

import com.springBoot1.SB2.entity.MedicineType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@ToString
public class UpdateMedicineDTO {

    private String scientificName;
    private String marketName;
    private Long category;
    private String description;
    private Double price;
    private MedicineType type;
    private Long supplier;
    private Long pharmacy;
    private String expiration;
    private MultipartFile img;

}
