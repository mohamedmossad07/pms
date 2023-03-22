package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springBoot1.SB2.entity.base.BaseTrashableAndAuditing;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Component
@ToString
@Table(name = "notifications")
public class Notification  extends BaseTrashableAndAuditing<Long> {
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @ManyToOne
    @JoinColumn(name = "medicine", nullable = false)
    @JsonBackReference
    private Medicine medicine;
    @OneToMany(mappedBy = "notification",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<UserNotification> users;
}