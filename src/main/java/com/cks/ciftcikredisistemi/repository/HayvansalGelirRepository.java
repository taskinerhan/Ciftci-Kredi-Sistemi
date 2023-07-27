package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGelir;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface HayvansalGelirRepository extends JpaRepository<HayvansalGelir,Long> {
    HayvansalGelir findHayvansalGelirById(Long id);
}
