package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HayvanIrkRepository extends JpaRepository<HayvanIrk,Long> {
    HayvanIrk findHayvanIrkById(Long id);



}
