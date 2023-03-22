package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends BaseRepository<Pharmacy, Long> {
}
