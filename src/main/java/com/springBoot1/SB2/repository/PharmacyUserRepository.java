package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.PharmacyUser;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PharmacyUserRepository extends BaseRepository<PharmacyUser, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete from pharmacy_users where pharmacy_id = ?1")
    void deleteByPharmacyId(Long id);
    @Modifying
    @Query(nativeQuery = true, value = "delete from pharmacy_users where user_id = ?1")
    void deleteByUserId(Long id);
}
