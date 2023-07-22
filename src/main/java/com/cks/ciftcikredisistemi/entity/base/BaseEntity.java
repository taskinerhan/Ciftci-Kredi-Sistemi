package com.cks.ciftcikredisistemi.entity.base;

import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {
    private Date kayitTarihi;
    private Date guncellemTarihi;
}
