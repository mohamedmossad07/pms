package com.springBoot1.SB2.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(value = {"created_by", "updated_by"}, allowGetters = true)
@EntityListeners({AuditingEntityListener.class})
public abstract class UserAuditing {
    private Long createdBy;
    private Long updatedBy;
}
