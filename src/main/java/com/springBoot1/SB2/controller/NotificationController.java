package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.dto.medicine.ShowAllMedicineDTO;
import com.springBoot1.SB2.dto.notification.ShowAllNotificationsDTO;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.service.NotificationService;
import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${API.URL_PREFIX}notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("")
    public ResponseEntity<?> index(@RequestParam(required = false, defaultValue = "id", value = "sort") String sort,
                                   @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                   @RequestParam(required = false, defaultValue = "10", value = "size") int size) throws ApiException {
        List<ShowAllNotificationsDTO> viewDTOS = notificationService.findAll(page, size, sort);
        return ApiResponseUtil.successPayload(viewDTOS, HttpStatus.OK);
    }
    @PostMapping("/make-as-read")
    public ResponseEntity<?> makeAsRead() throws ApiException {
        notificationService.makeNotifyAsRead();
        return ApiResponseUtil.successPayload(null, HttpStatus.OK);
    }
}
