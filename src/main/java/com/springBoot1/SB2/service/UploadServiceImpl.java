package com.springBoot1.SB2.service;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@Service
public class UploadServiceImpl implements UploadService {
    @Value("${uploadPaths.basePath}")
    private String basePath;
    @Value("${uploadPaths.pathPrefixCount}")
    private int pathPrefixCount;
    private Path getOrCreatePath(String path) throws IOException {
        Path newPath = Paths.get(basePath + path);
        if (!Files.exists(newPath)) {
            newPath = Files.createDirectories(newPath);
        }
        return newPath;
    }

    @Override
    public String upload(String path, MultipartFile img) throws IOException {
        Path path1 = getOrCreatePath(path);
        String filename = RandomString.make(pathPrefixCount) + "-" + img.getOriginalFilename();
        Files.copy(img.getInputStream(), path1.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        return path1 + File.separator + filename;
    }

    @Override
    public Resource load(String path) throws MalformedURLException {
        Path path1 = Paths.get(path);
        Resource resource = new UrlResource(path1.toUri());
        if (resource.exists() || resource.isReadable())
            return resource;
        else
            throw new RuntimeException("Couldn't read the file.");
    }
}
