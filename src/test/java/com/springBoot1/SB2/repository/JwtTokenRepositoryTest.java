package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Customer;
import com.springBoot1.SB2.entity.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class JwtTokenRepositoryTest {
    final JwtToken token = JwtToken.builder().token("TestToken").exp(1234543L).build();
    @Autowired
    JwtTokenRepository tokenRepository;
    @Test
    void assert_insert_token_and_find_it_to_DB(){
        log.info("JwtTokenRepositoryTest.assert_insert_token_and_find_it_to_DB");
        JwtToken savedToken = tokenRepository.save(token);
        assertTrue(tokenRepository.findByToken(token.getToken()).isPresent());
       tokenRepository.delete(savedToken);
    }
}