package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.category.CreateCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowAllCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowCategoryDTO;
import com.springBoot1.SB2.dto.category.UpdateCategoryDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${API.URL_PREFIX}categories")
public class CategoryController extends CURDTrashableController<Long, CreateCategoryDTO, ShowAllCategoryDTO, ShowCategoryDTO, UpdateCategoryDTO> {
}
