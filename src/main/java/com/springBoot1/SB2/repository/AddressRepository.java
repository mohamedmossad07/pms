package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {
}
