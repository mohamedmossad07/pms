package com.springBoot1.SB2.dto.category;

import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Setter
@Component
@Getter
public class ShowCategoryDTO extends BaseTrashableAuditingDTO {
    private String name;
    private ShowIdAndNameCategoryDTO parent=new ShowIdAndNameCategoryDTO();
    private Set<ShowIdAndNameCategoryDTO> subCategories=new HashSet<>();
}
