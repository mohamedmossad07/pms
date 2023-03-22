package com.springBoot1.SB2.dto.category;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import lombok.*;


@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShowAllCategoryDTO extends IdAndCreatedAtAuditingDTO {
    private String name;
}
