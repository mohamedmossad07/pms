package com.springBoot1.SB2.dto.medicine;

import com.springBoot1.SB2.entity.MedicineType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class CreateMedicineDTO {
    @NotBlank(message = "{medicines.scientificName.NotBlank}")
    @Size(min = 3, message = "{medicines.scientificName.Size}")
    private String scientificName;
    @NotBlank(message = "{medicines.marketName.NotBlank}")
    @Size(min = 3, message = "{medicines.marketName.Size}")
    private String marketName;
    @NotNull(message = "{medicines.category.NotNull}")
    private Long category;
    private String description;
    @NotNull(message = "{medicines.supplier.NotNull}")
    private Long supplier;
    private String expiration;
    @NotNull(message = "{medicines.price.NotNull}")
    private Double price;
    @NotNull(message = "{medicines.type.NotNull}")
    private MedicineType type;
    private MultipartFile img;
}
