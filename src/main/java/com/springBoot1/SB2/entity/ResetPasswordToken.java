package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springBoot1.SB2.entity.base.BaseIdAndAuditing;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "reset_password_tokens", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"token"})
})
public class ResetPasswordToken extends BaseIdAndAuditing<Long> {
    @Column(nullable = false, length = 50)
    private String token;
    @Column(nullable = false)
    private Long exp;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
