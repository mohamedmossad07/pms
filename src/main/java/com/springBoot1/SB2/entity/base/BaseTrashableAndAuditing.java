package com.springBoot1.SB2.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseTrashableAndAuditing<PK> extends BaseIdAndAuditing<PK> {
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean trashed;
}