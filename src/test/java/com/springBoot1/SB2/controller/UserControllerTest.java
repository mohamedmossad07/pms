package com.springBoot1.SB2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBoot1.SB2.dto.user.CreateUserDTO;
import com.springBoot1.SB2.dto.user.ShowAllUserDTO;
import com.springBoot1.SB2.dto.user.ShowUserDTO;
import com.springBoot1.SB2.dto.user.UpdateUserDTO;
import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.service.base.TrashableCRUDService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    void assert_consuming_users_create_endpoint() throws Exception {
//        Mockito.when(service.create(Mockito)).thenReturn(null);
        CreateUserDTO u =new CreateUserDTO();
        u.setFname("fname");
        u.setLname("mossadf");
        u.setUsername("fdewfgrew");
        u.setPassword("fdfewdewfgrew");
        u.setRole(1L);
        mockMvc.
        perform(MockMvcRequestBuilders.post("/api/v1/users/create")
                .contentType("application/json")
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb21vIiwiaWF0IjoxNjc5NTI3ODUyLCJleHAiOjE2ODA3Mzg5NTJ9.753RKLNdgpaFiHs-jyfRP715VRDyIqKrfue6QqrYd0m5rE-QWlBz3ekDFI05npOmEc-ix_nWyF5XA-Alir7OGA")
                .content(objectMapper.writeValueAsString(u)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}