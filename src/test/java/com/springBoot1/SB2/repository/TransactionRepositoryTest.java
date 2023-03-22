package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Customer;
import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;
    Customer customer = Customer.builder().fname("Mo2hamed").
            lname("msossad").email("teset@email.com").phone("0210101010").build();
    Address address = Address.builder().governorate("Caifsro1")
            .city("City1").town("0 discret").street("1El-fsKhalifa").build();
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PharmacyRepository pharmacyRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Test
    void assert_insert_transaction_then_drop_it_from_DB(){
        log.info("PharmacyRepositoryTest.assert_insert_transaction_then_drop_it_from_DB");
        final Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName("Pharma 1");
        pharmacy.setAddress(address);
        address  = addressRepository.save(address);
        Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);
        customer = customerRepository.save(customer);
        Transaction transaction = Transaction.builder().price(343.3).customer(customer).pharmacy(savedPharmacy).build();
        assertEquals(343.3,transaction.getPrice());
        transactionRepository.delete(transaction);
        customerRepository.delete(customer);
        pharmacyRepository.delete(pharmacy);
        addressRepository.delete(address);
    }
}