package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class MedicineRepositoryTest {
    Address address = Address.builder().governorate("Caifsro1")
            .city("City1").town("0 discret").street("1El-fsKhalifa").build();
    Category category = Category.builder().name("Test1 Cafdstegory").build();
    Supplier supplier = new Supplier();
    final Medicine medicine = Medicine.builder().scientificName("Ervdsr4").marketName("medark")
            .category(category).price(333.3).description("dffvewfre")
            .supplier(supplier).type(MedicineType.TYPE1).build();
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Test
    void assert_insert_medicine_then_drop_it_from_DB(){
        log.info("MedicineRepositoryTest.assert_insert_medicine_then_drop_it_from_DB");
        supplier.setAddress(address);
        supplier.setEmail("emaedfedd3il@r4e.fre");
        supplier.setName("Tesededdt Supplier");
        address  = addressRepository.save(address);
        category = categoryRepository.save(category);
        supplier = supplierRepository.save(supplier);
        Medicine savedMedicine = medicineRepository.save(medicine);
        assertEquals(medicine.getMarketName(),savedMedicine.getMarketName());
        medicineRepository.delete(savedMedicine);
        supplierRepository.delete(supplier);
        categoryRepository.delete(category);
        addressRepository.delete(address);
    }
}