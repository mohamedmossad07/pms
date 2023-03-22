package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Authority;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends BaseRepository<Authority, Long> {
}
