package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springBoot1.SB2.entity.base.BaseIdAndAuditing;
import com.springBoot1.SB2.entity.base.DateAndUserAuditing;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Component
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pharmacy_users")
public class PharmacyUser extends BaseIdAndAuditing<Long> {
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pharmacy_id")
    @JsonBackReference
    private Pharmacy pharmacy;
}
