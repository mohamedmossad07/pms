package com.springBoot1.SB2.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
public abstract class DateAndUserAuditing extends DateAuditing {
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
}
