package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlikGelir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HayvansalVarlikGelirRepository extends JpaRepository<HayvansalVarlikGelir, Long> {
}
