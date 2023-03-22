package com.springBoot1.SB2.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface UploadService {
    String upload(String path, MultipartFile file) throws IOException;

    Resource load(String path) throws MalformedURLException;
}
