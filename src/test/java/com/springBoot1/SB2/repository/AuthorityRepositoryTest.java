package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Authority;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class AuthorityRepositoryTest {
    final Authority authority = Authority.builder().name("test_Authority").build();
    @Autowired
    AuthorityRepository authorityRepository;
    @Test
    void assert_authority_saved_and_deleted_to_DB(){
        log.info("AuthorityRepositoryTest.assert_authority_saved_and_deleted_to_DB");
        Authority savedAuthority = authorityRepository.save(authority);
        assertEquals(authority.getName(),savedAuthority.getName());
        authorityRepository.delete(savedAuthority);
    }
}