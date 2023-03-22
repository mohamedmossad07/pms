package com.springBoot1.SB2.mapper.category;

import com.springBoot1.SB2.dto.category.CreateCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowAllCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowCategoryDTO;
import com.springBoot1.SB2.dto.category.UpdateCategoryDTO;
import com.springBoot1.SB2.entity.Category;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = {IdsMapper.class},nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper extends BaseMapper<Category, ShowCategoryDTO, ShowAllCategoryDTO, CreateCategoryDTO, UpdateCategoryDTO> {}
