package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HayvansalVarlikRepository extends JpaRepository<HayvansalVarlik, Long> {
    List<HayvansalVarlik> findByCiftciVarlikId(Long id);

    List<HayvansalVarlik> findByCiftciVarlikAndHayvanIrk(CiftciVarlik ciftciVarlik, HayvanIrk hayvanIrk);
}
