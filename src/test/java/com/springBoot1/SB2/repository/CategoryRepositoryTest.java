package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Authority;
import com.springBoot1.SB2.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CategoryRepositoryTest {
    final Category category = Category.builder().name("Test Category0").build();
    @Autowired
    CategoryRepository categoryRepository;
    @Test
    void assert_category_saved_and_deleted_to_DB(){
        log.info("CategoryRepositoryTest.assert_category_saved_and_deleted_to_DB");
        Category savedCategory = categoryRepository.save(category);
        assertEquals(category.getName(),savedCategory.getName());
        categoryRepository.delete(savedCategory);
    }
}