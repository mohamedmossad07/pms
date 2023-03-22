package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.ResetPasswordToken;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetPasswordTokenRepository extends BaseRepository<ResetPasswordToken, Long> {
    Optional<ResetPasswordToken> findByToken(String token);
}
