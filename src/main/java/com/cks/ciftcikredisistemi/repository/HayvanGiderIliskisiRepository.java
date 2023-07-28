package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGiderIliskisi;
import com.cks.ciftcikredisistemi.enums.Cinsiyet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HayvanGiderIliskisiRepository extends JpaRepository<HayvansalGiderIliskisi, Long> {

    List<HayvansalGiderIliskisi> findByHayvanIrk(HayvanIrk hayvanIrk);

    List<HayvansalGiderIliskisi> findByHayvanIrkAndCinsiyet(HayvanIrk hayvanIrk, Cinsiyet cinsiyet);
}
