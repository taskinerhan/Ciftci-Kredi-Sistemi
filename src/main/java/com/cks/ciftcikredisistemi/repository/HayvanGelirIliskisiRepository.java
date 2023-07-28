package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.hayvan.HayvanGelirIliskisi;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import com.cks.ciftcikredisistemi.enums.Cinsiyet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface HayvanGelirIliskisiRepository extends JpaRepository<HayvanGelirIliskisi, Long> {
    List<HayvanGelirIliskisi> findByHayvanIrk(HayvanIrk hayvanIrk);

    List<HayvanGelirIliskisi> findByHayvanIrkIdAndCinsiyet(HayvanIrk hayvanIrk, Cinsiyet cinsiyet);
}
