package com.springBoot1.SB2.dto.category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCategoryDTO {
    private String name;
    private Long parent;
}
