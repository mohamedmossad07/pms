package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;
    final Role role = Role.builder().name("TestRole")
            .priority((short) 2).build();

    @Test
    void assert_insert_role_then_drop_it_from_DB() {
        log.info("RoleRepositoryTest.assert_insert_role_then_drop_it_from_DB");
        Role savedRole = roleRepository.save(role);
        assertEquals(role.getName(),savedRole.getName());
        roleRepository.delete(savedRole);
    }
}