package com.springBoot1.SB2.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseIdAndAuditing<PK> extends DateAndUserAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;
}
