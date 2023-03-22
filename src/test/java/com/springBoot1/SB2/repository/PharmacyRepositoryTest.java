package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Medicine;
import com.springBoot1.SB2.entity.Pharmacy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class PharmacyRepositoryTest {
    Address address = Address.builder().governorate("Caifsro1")
            .city("City1").town("0 discret").street("1El-fsKhalifa").build();
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PharmacyRepository pharmacyRepository;
    @Test
    void assert_insert_pharmacy_then_drop_it_from_DB(){
        log.info("PharmacyRepositoryTest.assert_insert_pharmacy_then_drop_it_from_DB");
        final Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName("Pharma 1");
        pharmacy.setAddress(address);
        address  = addressRepository.save(address);
        Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);
        assertEquals(pharmacy.getName(),savedPharmacy.getName());
        pharmacyRepository.delete(pharmacy);
        addressRepository.delete(address);
    }
}