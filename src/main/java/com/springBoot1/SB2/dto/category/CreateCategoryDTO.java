package com.springBoot1.SB2.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CreateCategoryDTO {
    @NotBlank(message = "{categories.name.NotBlank}")
    private String name;
    private Long parent;
}
