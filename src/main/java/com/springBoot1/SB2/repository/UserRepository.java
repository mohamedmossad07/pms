package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByPhone(String phone);

    User findByUsername(String username);
}
