package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface HayvanIrkRepository extends JpaRepository<HayvanIrk,Long> {
    HayvanIrk findHayvanIrkById(Long id);
}
