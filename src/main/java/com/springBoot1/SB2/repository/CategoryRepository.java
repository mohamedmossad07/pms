package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Category;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
