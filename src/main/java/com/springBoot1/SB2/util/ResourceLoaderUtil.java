package com.springBoot1.SB2.util;

import com.springBoot1.SB2.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

@Component
public class ResourceLoaderUtil {
    @Autowired
    protected UploadService uploadService;
    public void loadAndWriteFile(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
            Resource resource = uploadService.load(filename);
            String contentType = "application/octet-stream";
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(String.valueOf(MediaType.parseMediaType(contentType)));
            response.setContentLength(resource.getInputStream().readAllBytes().length);
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + resource.getFilename() + "\"");
            ServletOutputStream out = response.getOutputStream();
            out.write(resource.getInputStream().readAllBytes());
            out.flush();
            out.close();
    }
}
