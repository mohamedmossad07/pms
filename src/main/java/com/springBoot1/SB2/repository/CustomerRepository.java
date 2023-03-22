package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Customer;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
    Optional<Customer> findByFnameAndLname(String fname, String lname);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);
}
