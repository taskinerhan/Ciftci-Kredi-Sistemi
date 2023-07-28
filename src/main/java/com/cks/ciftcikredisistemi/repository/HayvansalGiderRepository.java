package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HayvansalGiderRepository extends JpaRepository<HayvansalGider,Long> {
    HayvansalGider findHayvansalGiderById(Long id);
}
