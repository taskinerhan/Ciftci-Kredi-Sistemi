package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlikGider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HayvansalVarlikGiderRepository extends JpaRepository<HayvansalVarlikGider, Long> {
}
