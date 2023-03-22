package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.controller.base.CURDTrashableController;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.user.*;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.service.UserService;
import com.springBoot1.SB2.util.ApiResponseUtil;
import com.springBoot1.SB2.util.ArrayUtil;
import com.springBoot1.SB2.util.ResourceLoaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("${API.URL_PREFIX}users")
public class UserController extends CURDTrashableController<Long, CreateUserDTO, ShowAllUserDTO, ShowUserDTO, UpdateUserDTO> {
    @Autowired
    private ResourceLoaderUtil loaderUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseEntity<?> create(@ModelAttribute @Valid CreateUserDTO createUserDTO) throws  UnAuthorizedAccessException, ApiException {
        return super.create(createUserDTO);
    }

    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long aLong, @ModelAttribute @Valid UpdateUserDTO updateUserDTO) throws  UnAuthorizedAccessException, ApiException {
        return super.update(aLong, updateUserDTO);
    }

    @GetMapping("/pharmacies")
    public ResponseEntity<?> currentUserPharmacies() {
        return ApiResponseUtil.successPayload(userService.currentUserPharmacies(), HttpStatus.OK);
    }

    @PostMapping("/reset-password/check-expiration")
    public ResponseEntity<?> checkResetPasswordTokenExpiration(@RequestBody @Valid ResetPasswordTokenDTO tokenDTO){
        boolean isValid = userService.isResetTokenValid(tokenDTO.getToken());
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", isValid);
        return ApiResponseUtil.successPayload(map, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordDTO passwordDTO) throws  MessagingException, ApiException {
        String token = userService.resetPasswordByEmail(passwordDTO.getEmail(), passwordDTO.getLink());
        if (Objects.isNull(token)) {
            return ApiResponseUtil.errorPayload(messageSource.getMessage("resetPassword.contactAdmin", ArrayUtil.of(), LocaleContextHolder.getLocale()),
                    HttpStatus.BAD_REQUEST);
        }
        return ApiResponseUtil.successPayload(null, HttpStatus.OK);
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePasswordByToken(@RequestBody @Valid UpdatePasswordDTO updatePasswordDTO){
        boolean updated = userService.updatePasswordByToken(updatePasswordDTO.getToken(),
                updatePasswordDTO.getPassword());
        if (!updated) {//token not valid
            return ApiResponseUtil.errorPayload(messageSource.getMessage("updatePassword.tryAgain", ArrayUtil.of(), LocaleContextHolder.getLocale()), HttpStatus.BAD_REQUEST);
        }
        return ApiResponseUtil.successPayload(messageSource.getMessage("updatePassword.successful", ArrayUtil.of(), LocaleContextHolder.getLocale()), HttpStatus.OK);
    }
    @GetMapping("/load-file")
    public void loadFile(@RequestParam(value = "file") String filename) throws IOException {
        loaderUtil.loadAndWriteFile(filename,request,response);
    }
}
