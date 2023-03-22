package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class SupplierRepositoryTest {
    Address address = Address.builder().governorate("Caifsro1")
            .city("City1").town("0 discret").street("1El-fsKhalifa").build();
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Test
    void assert_insert_medicine_then_drop_it_from_DB(){
        log.info("MedicineRepositoryTest.assert_insert_medicine_then_drop_it_from_DB");
        Supplier supplier = new Supplier();
        supplier.setAddress(address);
        supplier.setEmail("emaedfedd3il@r4e.fre");
        supplier.setName("Tesededdt Supplier");
        address  = addressRepository.save(address);
        supplier = supplierRepository.save(supplier);
        supplierRepository.delete(supplier);
        addressRepository.delete(address);
    }
}