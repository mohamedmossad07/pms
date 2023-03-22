package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Test
    void assert_insert_user_then_drop_it_from_DB() {
        log.info("UserRepositoryTest.assert_insert_user_then_drop_it_from_DB");
        final User user = new User();
        user.setFname("Mohamed");
        user.setLname("mossad");
        user.setUsername("mohmos");
        user.setEmail("dewqdfew@frve.frwe");
        user.setPhone("34567865");
        user.setPassword("frwsfgr");

        User savedUser = userRepository.save(user);
        assertEquals(user.getEmail(),savedUser.getEmail());
        userRepository.delete(savedUser);
    }
}