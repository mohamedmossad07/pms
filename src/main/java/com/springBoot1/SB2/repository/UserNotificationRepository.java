package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.Notification;
import com.springBoot1.SB2.entity.UserNotification;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserNotificationRepository extends BaseRepository<UserNotification,Long> {
     Page<UserNotification> findByUserId(Long user, Pageable pageable);
     @Modifying
     @Query(nativeQuery = true, value = "UPDATE user_notifications SET as_read = 1 where user_id = ?1")
     void makeNotifyAsRead(Long user);
}
