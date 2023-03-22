package com.springBoot1.SB2.dto.authority;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
public class AddOrUpdateAuthorityDTO {
    @Size(min = 2, max = 200, message = "{authorities.name.Size}")
    @NotBlank(message = "{authorities.name.Size}")
    private String name;
}
