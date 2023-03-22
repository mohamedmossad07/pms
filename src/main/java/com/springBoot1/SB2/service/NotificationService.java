package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.config.UserDetailsImpl;
import com.springBoot1.SB2.dto.medicine.ShowAllMedicineDTO;
import com.springBoot1.SB2.dto.notification.ShowAllNotificationsDTO;
import com.springBoot1.SB2.entity.Medicine;
import com.springBoot1.SB2.entity.UserNotification;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.repository.UserNotificationRepository;
import com.springBoot1.SB2.repository.base.BaseRepository;
import com.springBoot1.SB2.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserNotificationRepository userNotificationRepository;
    public List<ShowAllNotificationsDTO> findAll(int page, int size, String sort) throws ApiException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<UserNotification> data = userNotificationRepository.findByUserId(userDetails.getUser().getId(),pageable);
        return data.getContent().stream().map(userNotification -> {
            ShowAllNotificationsDTO notificationsDTO=new ShowAllNotificationsDTO();
            notificationsDTO.setCreatedAt(userNotification.getCreatedAt());
            String msg="";
            switch (userNotification.getNotification().getType()){
                case MEDICINE_COUNT_CHECKING:
                    msg=messageSource.getMessage("notifications.MEDICINE_COUNT_CHECKING.msg", ArrayUtil.of(userNotification.getUser().getFname()+" "+userNotification.getUser().getLname(),userNotification.getNotification().getMedicine().getId()+"-"+userNotification.getNotification().getMedicine().getMarketName()), LocaleContextHolder.getLocale());
                    break;
                case MEDICINE_EXPIRATION:
                    msg=messageSource.getMessage("notifications.MEDICINE_EXPIRATION.msg", ArrayUtil.of(userNotification.getUser().getFname()+" "+userNotification.getUser().getLname(),userNotification.getNotification().getMedicine().getId()+"-"+userNotification.getNotification().getMedicine().getMarketName()), LocaleContextHolder.getLocale());
                    break;
                default:
                    break;
            }
            notificationsDTO.setMessage(msg);
            notificationsDTO.setRead(userNotification.isAsRead());
            notificationsDTO.setId(userNotification.getNotification().getId());
            return  notificationsDTO;
        }).collect(Collectors.toList());
    }

    public void makeNotifyAsRead() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userNotificationRepository.makeNotifyAsRead(userDetails.getUser().getId());
    }
}
