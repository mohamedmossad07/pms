package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.JwtToken;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtTokenRepository extends BaseRepository<JwtToken, Long> {
    Optional<JwtToken> findByToken(String token);
}
