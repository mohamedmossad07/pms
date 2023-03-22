package com.springBoot1.SB2.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UploadServiceImplTest {
    @Autowired
    UploadService uploadService;
    @Test
    void assert_loading_file_exits_in_filesystem() throws MalformedURLException {
        String path = "/home/mohamed/Documents/projects/pharmacy/uploads/users/wJg2Um61Tb-Screenshot from 2022-08-21 20-05-29.png";
        Resource resource = uploadService.load(path);
        assertTrue(resource.exists());
    }
}