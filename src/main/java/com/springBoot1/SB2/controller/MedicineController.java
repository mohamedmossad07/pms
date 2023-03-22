package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.medicine.*;
import com.springBoot1.SB2.entity.MedicineType;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.service.MedicineServiceImpl;
import com.springBoot1.SB2.util.ApiResponseUtil;
import com.springBoot1.SB2.util.ResourceLoaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${API.URL_PREFIX}medicines")
public class MedicineController extends CURDTrashableController<Long, CreateMedicineDTO, ShowAllMedicineDTO, ShowMedicineDTO, UpdateMedicineDTO> {
    @Autowired
    private ResourceLoaderUtil loaderUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private MedicineServiceImpl medicineService;

    @Override
    public ResponseEntity<?> create(@ModelAttribute @Valid CreateMedicineDTO createMedicineDTO) throws  UnAuthorizedAccessException, ApiException {
        return super.create(createMedicineDTO);
    }

    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long aLong, @ModelAttribute @Valid UpdateMedicineDTO updateMedicineDTO) throws  UnAuthorizedAccessException, ApiException {
        return super.update(aLong, updateMedicineDTO);
    }
    @GetMapping("error")
    @Override
    public ResponseEntity<?> index(String sort, int page, int size) {
        return null;
    }
    @GetMapping("error2")
    @Override
    public ResponseEntity<?> show(Long aLong) throws  UnAuthorizedAccessException, ApiException {
        return null;
    }
    @GetMapping("")
    public ResponseEntity<?> index(@RequestParam(required = false, defaultValue = "id", value = "sort") String sort,
                                   @RequestParam(required = true, defaultValue = "0", value = "page") int page,
                                   @RequestParam(required = false, defaultValue = "10", value = "size") int size,
                                   @RequestParam(required = true,value = "pharmacy") Long pharmacy) throws  ApiException {
        List<ShowAllMedicineDTO> viewDTOS = medicineService.findAll(page, size, sort,pharmacy);
        return ApiResponseUtil.successPayload(viewDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id,@RequestParam(required = true,value = "pharmacy") Long pharmacy) throws  UnAuthorizedAccessException, ApiException {
        ShowMedicineDTO viewDTO = medicineService.show(id,pharmacy);
        return ApiResponseUtil.successPayload(viewDTO, HttpStatus.FOUND);
    }
    @PreAuthorize("hasAuthority('" + Authority.MEDICINESHOWTYPES + "')")
    @GetMapping("/types")
    public ResponseEntity<?> getTypes() {
        return ApiResponseUtil.successPayload(MedicineType.values(), HttpStatus.OK);
    }

    @PostMapping("/update-count")
    public ResponseEntity<?> updateCount(@RequestBody @Valid UpdateMedicineCount updateMedicineCount) throws  ApiException {
        medicineService.updateCount(updateMedicineCount);
        return ApiResponseUtil.successPayload(null, HttpStatus.OK);
    }
    @GetMapping("/load-file")
    public void loadFile(@RequestParam(value = "file") String filename) throws IOException {
        loaderUtil.loadAndWriteFile(filename,request,response);
    }
}
