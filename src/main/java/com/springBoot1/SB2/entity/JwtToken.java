package com.springBoot1.SB2.entity;

import com.springBoot1.SB2.entity.base.BaseIdAndAuditing;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "jwt_tokens", uniqueConstraints = {@UniqueConstraint(columnNames = "token")})
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken extends BaseIdAndAuditing<Long> {
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private Long exp;
}
