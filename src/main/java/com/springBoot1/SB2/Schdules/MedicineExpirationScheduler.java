package com.springBoot1.SB2.Schdules;

import com.springBoot1.SB2.entity.PharmacyMedicine;
import com.springBoot1.SB2.event.MedicineExpirationEvent;
import com.springBoot1.SB2.repository.NotificationRepository;
import com.springBoot1.SB2.repository.PharmacyMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class MedicineExpirationScheduler {
    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Value("${Scheduling.daysBeforeAlert}")
    private int daysBeforeAlert;
    @Scheduled(fixedDelay = 59000)
    @Transactional
    public void scheduleMedicineExpiration(){
//        System.out.println("==scheduleMedicineExpiration=>"+LocalDateTime.now());
        List<PharmacyMedicine> pharmacyMedicines=
                pharmacyMedicineRepository.findAll();
        pharmacyMedicines.forEach(pharmacyMedicine -> {
            if(pharmacyMedicine.getExpiration()!=null&&LocalDate.now().isAfter(pharmacyMedicine.getExpiration().minusDays(daysBeforeAlert))){
                System.out.println("Alert: On pharmacy "+
                        pharmacyMedicine.getPharmacy().getId().toString()+
                        " and medicine "+pharmacyMedicine.getMedicine().getId().toString());
            eventPublisher.publishEvent(new MedicineExpirationEvent(pharmacyMedicine.getMedicine().getId(),pharmacyMedicine.getPharmacy().getId()));}
        });
    }
}
