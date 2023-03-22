package com.springBoot1.SB2.controller.base;

import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.service.UploadService;
import com.springBoot1.SB2.service.base.TrashableCRUDService;
import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.persistence.MappedSuperclass;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

@MappedSuperclass
@CrossOrigin(origins = "http://localhost:3000")
public abstract class CURDTrashableController<ID, CreateDTO, ViewAllDTO, ViewDTO, UpdateDTO> implements ApiCRUDController<ID, CreateDTO, ViewDTO, UpdateDTO>, ApiTrashableController<ID> {
    @Autowired
    protected TrashableCRUDService<ID, CreateDTO, ViewAllDTO, ViewDTO, UpdateDTO> service;
    @GetMapping("")
    public ResponseEntity<?> index(@RequestParam(required = false, defaultValue = "id", value = "sort") String sort,
                                   @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                   @RequestParam(required = false, defaultValue = "10", value = "size") int size) {
        List<ViewAllDTO> viewDTOS = service.findAll(page, size, sort);
        return ApiResponseUtil.successPayload(viewDTOS, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateDTO createDTO) throws  UnAuthorizedAccessException, ApiException {
        service.create(createDTO);
        return ApiResponseUtil.successPayload(null, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") ID id) throws  UnAuthorizedAccessException, ApiException {
        ViewDTO viewDTO = service.show(id);
        return ApiResponseUtil.successPayload(viewDTO, HttpStatus.FOUND);
    }

    @PatchMapping("/restore/{id}")
    public ResponseEntity<?> restore(@PathVariable("id") ID id) throws  UnAuthorizedAccessException, ApiException {
        service.restore(id);
        return ApiResponseUtil.successPayload(null, HttpStatus.OK);
    }

    @DeleteMapping("/trash/{id}")
    public ResponseEntity<?> trash(@PathVariable("id") ID id) throws  UnAuthorizedAccessException, ApiException {
        service.trash(id);
        return ApiResponseUtil.successPayload(null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") ID id, @RequestBody @Valid UpdateDTO updateDTO) throws  UnAuthorizedAccessException, ApiException {
        service.update(id, updateDTO);
        return ApiResponseUtil.successPayload(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ID id) throws  UnAuthorizedAccessException, ApiException {
        service.delete(id);
        return ApiResponseUtil.successPayload(null, HttpStatus.OK);
    }

//    @GetMapping("/load-file")
//    public void loadFile(@RequestParam(value = "file") String filename){
//        try {
//            Resource resource = uploadService.load(filename);
//            String contentType = "application/octet-stream";
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//            response.setStatus(HttpStatus.OK.value());
//            response.setContentType(String.valueOf(MediaType.parseMediaType(contentType)));
//            response.setContentLength(resource.getInputStream().readAllBytes().length);
//            response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
//                    "attachment; filename=\"" + resource.getFilename() + "\"");
//            ServletOutputStream out = response.getOutputStream();
//            out.write(resource.getInputStream().readAllBytes());
//            out.flush();
//            out.close();
////            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION,
////                    "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
//        } catch (IOException | RuntimeException e) {
////            return ApiResponseUtil.errorPayload(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
