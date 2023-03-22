package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class AddressRepositoryTest {
      final Address address = Address.builder().governorate("Cairo")
              .city("2city").town("2 discret").street("El-Khalifa").build();
      @Autowired
      AddressRepository addressRepository;
      @Test
      void  assert_address_saved_and_deleted_to_DB(){
          log.info("AddressRepositoryTest.assert_address_saved_and_deleted_to_DB");
          Address savedAddress  = addressRepository.save(address);
           assertEquals(address.getGovernorate(),savedAddress.getGovernorate());
           assertEquals(address.getCity(),savedAddress.getCity());
           assertEquals(address.getTown(),savedAddress.getTown());
           assertEquals(address.getStreet(),savedAddress.getStreet());
           addressRepository.delete(savedAddress);
      }
}