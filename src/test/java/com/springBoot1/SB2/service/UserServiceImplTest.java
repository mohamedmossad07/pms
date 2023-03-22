package com.springBoot1.SB2.service;

import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.event.ResetPasswordEmailSenderEvent;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import javax.mail.MessagingException;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserServiceImplTest {
    @Autowired
    UserService userService;
    @MockBean
    UserRepository  userRepository;
//    @Test
    void assert_return_a_50char_token_and_link_when_resting_password_by_email() throws MessagingException, ApiException {
        User u = new User();
        u.setFname("mohamed");
        u.setLname("mossad");
        u.setEmail("test@ffre.fre");
        Optional<User> user =Optional.of(u);
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(null);
        String token = userService.resetPasswordByEmail("test@test.com","link");
        assertEquals(50,token.length());
    }
}