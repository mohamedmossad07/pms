package com.springBoot1.SB2.event.listener;

import com.springBoot1.SB2.entity.*;
import com.springBoot1.SB2.event.BaseEmailSenderEvent;
import com.springBoot1.SB2.event.MedicineCountCheckingEvent;
import com.springBoot1.SB2.event.MedicineExpirationEvent;
import com.springBoot1.SB2.event.SendEmailToSupplierEvent;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.ApiRequestPayloadException;
import com.springBoot1.SB2.repository.NotificationRepository;
import com.springBoot1.SB2.repository.PharmacyMedicineRepository;
import com.springBoot1.SB2.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NotificationListeners{
    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${notifications.fromEmailToSupplier}")
    private String emailFrom;
    @Value("${notifications.html}")
    private boolean html;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @EventListener(classes = {MedicineCountCheckingEvent.class})
    public void handleMedicineCountCheckingEvent(MedicineCountCheckingEvent event) throws  ApiException {
        System.out.println("=========handler======================");
        PharmacyMedicine pharmacyMedicine = pharmacyMedicineRepository
                .findByPharmacyIdAndMedicineId(event.getPharmacyId(),event.getMedicineId())
                .orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(event.getMedicineId().toString()), LocaleContextHolder.getLocale())));
        //create notification
        Notification notification=new Notification();
        notification.setType(NotificationType.MEDICINE_COUNT_CHECKING);
        notification.setMedicine(pharmacyMedicine.getMedicine());
        Set<User>users= pharmacyMedicine.getPharmacy().getUsers().stream().map(PharmacyUser::getUser).collect(Collectors.toSet());
        if(pharmacyMedicine.getPharmacy().getManager()!=null){
            users.add(pharmacyMedicine.getPharmacy().getManager());}
//        Notification notification1 = notificationRepository.save(notification);
        notification.setUsers(users.stream().map(user ->new UserNotification(user,notification, false)).collect(Collectors.toSet()));
        notificationRepository.save(notification);
        Supplier supplier=pharmacyMedicine.getMedicine().getSupplier();
        SendEmailToSupplierEvent event1 = new SendEmailToSupplierEvent(
                emailFrom,
                supplier.getEmail(),
                messageSource.getMessage("notificationListeners.email.subj", ArrayUtil.of(), LocaleContextHolder.getLocale()),
                messageSource.getMessage("notificationListeners.email.text", ArrayUtil.of(), LocaleContextHolder.getLocale()),
                html);
        eventPublisher.publishEvent(event1);
    }
    @EventListener(classes = {BaseEmailSenderEvent.class})
    @Async
    public void handleBaseEmailSenderEvent(BaseEmailSenderEvent event) throws MessagingException {
        System.out.println("----+++handleSendEmailToSupplierEvent++-------");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, event.isHtml());
        messageHelper.setFrom(event.getFrom());
        messageHelper.setTo(event.getTo());
        messageHelper.setSubject(event.getSubject());
        messageHelper.setText( event.getText(),event.isHtml());
        mailSender.send(mimeMessage);
    }
    @EventListener(classes = {MedicineExpirationEvent.class})
    public void handleMedicineExpirationEvent(MedicineExpirationEvent event) throws ApiRequestPayloadException, ApiException {
        System.out.println("=========MedicineExpirationEvent======================");
        PharmacyMedicine pharmacyMedicine = pharmacyMedicineRepository
                .findByPharmacyIdAndMedicineId(event.getPharmacyId(),event.getMedicineId())
                .orElseThrow(() ->new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(event.getMedicineId().toString()), LocaleContextHolder.getLocale())) );
        //create notification
        Notification notification=new Notification();
        notification.setType(NotificationType.MEDICINE_EXPIRATION);
        notification.setMedicine(pharmacyMedicine.getMedicine());
        Set<User>users= pharmacyMedicine.getPharmacy().getUsers().stream().map(PharmacyUser::getUser).collect(Collectors.toSet());
        if(pharmacyMedicine.getPharmacy().getManager()!=null){
            users.add(pharmacyMedicine.getPharmacy().getManager());}
//        Notification notification1 = notificationRepository.save(notification);
        notification.setUsers(users.stream().map(user ->new UserNotification(user,notification, false)).collect(Collectors.toSet()));
        notificationRepository.save(notification);
    }

}
