package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springBoot1.SB2.entity.base.BaseIdAndAuditing;
import com.springBoot1.SB2.entity.base.DateAndUserAuditing;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_notifications")
public class UserNotification extends BaseIdAndAuditing<Long> {
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @ManyToOne
    @JoinColumn(name = "notification_id")
    @JsonBackReference
    private Notification notification;
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean asRead;
}