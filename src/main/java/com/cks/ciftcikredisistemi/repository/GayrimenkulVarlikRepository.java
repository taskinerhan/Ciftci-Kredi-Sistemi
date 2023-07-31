package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.entity.gayrimenkul.GayrimenkulVarlik;
import com.cks.ciftcikredisistemi.enums.GayrimenkulTipi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GayrimenkulVarlikRepository extends JpaRepository<GayrimenkulVarlik, Long> {
    List<GayrimenkulVarlik> findByCiftciVarlikId(Long id);

    List<GayrimenkulVarlik> findByCiftciVarlikAndGayrimenkulTipi(CiftciVarlik ciftciVarlik, GayrimenkulTipi gayrimenkulTipi);

}