package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Category;
import com.springBoot1.SB2.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CustomerRepositoryTest {
    final Customer customer = Customer.builder().fname("Mo2hamed").
        lname("msossad").email("teset@email.com").phone("0210101010").build();
    @Autowired
    CustomerRepository customerRepository;
    @Test
    void assert_insert_customer_and_find_it_byEmail_and_byPhone_to_DB(){
        log.info("CustomerRepositoryTest.assert_insert_customer_and_find_it_byEmail_to_DB");
        Customer savedCustomer = customerRepository.save(customer);
        assertTrue(customerRepository.findByFnameAndLname(customer.getFname(),customer.getLname()).isPresent());
        assertTrue(customerRepository.findByEmail(customer.getEmail()).isPresent());
        assertTrue(customerRepository.findByPhone(customer.getPhone()).isPresent());
        customerRepository.delete(savedCustomer);
    }
}