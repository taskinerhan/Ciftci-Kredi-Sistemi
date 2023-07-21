package com.cks.ciftcikredisistemi.entity.base;

import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    private Date kayitTarihi;
    private Date guncellemTarihi;
}
