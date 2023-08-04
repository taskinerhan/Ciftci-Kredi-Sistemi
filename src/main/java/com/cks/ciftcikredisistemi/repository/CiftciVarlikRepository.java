package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiftciVarlikRepository extends JpaRepository<CiftciVarlik, Long> {
    CiftciVarlik findCiftciVarlikById(Long id);
}
