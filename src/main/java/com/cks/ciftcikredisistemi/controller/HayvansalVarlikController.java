package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.HayvansalVarlikDto;
import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvanGelirIliskisi;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGelir;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGider;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGiderIliskisi;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlik;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlikGelir;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlikGider;
import com.cks.ciftcikredisistemi.repository.CiftciVarlikRepository;
import com.cks.ciftcikredisistemi.repository.HayvanGelirIliskisiRepository;
import com.cks.ciftcikredisistemi.repository.HayvanGiderIliskisiRepository;
import com.cks.ciftcikredisistemi.repository.HayvanIrkRepository;
import com.cks.ciftcikredisistemi.repository.HayvansalVarlikGelirRepository;
import com.cks.ciftcikredisistemi.repository.HayvansalVarlikGiderRepository;
import com.cks.ciftcikredisistemi.repository.HayvansalVarlikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hayvansal-varlik-yonetimi")
public class HayvansalVarlikController {
    private final CiftciVarlikRepository ciftciVarlikRepository;
    private final HayvanIrkRepository hayvanIrkRepository;
    private final HayvansalVarlikRepository hayvansalVarlikRepository;
    private final HayvanGelirIliskisiRepository hayvanGelirIliskisiRepository;
    private final HayvanGiderIliskisiRepository hayvanGiderIliskisiRepository;
    private final HayvansalVarlikGiderRepository hayvansalVarlikGiderRepository;
    private final HayvansalVarlikGelirRepository hayvansalVarlikGelirRepository;

    @PostMapping("")
    public ResponseEntity<?> hayvanVarlikEkle(@RequestBody HayvansalVarlikDto hayvansalVarlikDto) {
        if (hayvansalVarlikDto == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Girilen bilgiler geçersiz veya eksik.");
        }
        HayvansalVarlik hayvansalVarlik = new HayvansalVarlik();
        hayvansalVarlik.setHayvanAdeti(hayvansalVarlikDto.getHayvanAdeti());
        hayvansalVarlik.setCinsiyet(hayvansalVarlikDto.getCinsiyet());

        CiftciVarlik ciftciVarlik = ciftciVarlikRepository.findCiftciVarlikById(hayvansalVarlikDto.getCiftciVarlikId());
        hayvansalVarlik.setCiftciVarlik(ciftciVarlik);

        HayvanIrk hayvanIrk = hayvanIrkRepository.findHayvanIrkById(hayvansalVarlikDto.getHayvanIrkId());
        hayvansalVarlik.setHayvanIrk(hayvanIrk);
        hayvansalVarlikRepository.save(hayvansalVarlik);

        List<HayvanGelirIliskisi> hayvanGelirIliskisiList = hayvanGelirIliskisiRepository.findByHayvanIrkAndCinsiyet(hayvanIrk, hayvansalVarlikDto.getCinsiyet());
        for (HayvanGelirIliskisi hayvanGelirIliskisi : hayvanGelirIliskisiList) {
            HayvansalVarlikGelir hayvansalVarlikGelir = new HayvansalVarlikGelir();
            HayvansalGelir hayvansalGelir = hayvanGelirIliskisi.getHayvansalGelir();
            BigDecimal toplamGelir = hayvansalGelir.getBirimFiyat().multiply(hayvanGelirIliskisi.getMiktar());
            toplamGelir = hayvansalVarlik.getHayvanAdeti().multiply(toplamGelir);

            hayvansalVarlikGelir.setAd(hayvansalGelir.getAd());
            hayvansalVarlikGelir.setMiktar(hayvanGelirIliskisi.getMiktar());
            hayvansalVarlikGelir.setToplamFiyat(toplamGelir);
            hayvansalVarlikGelir.setHayvansalVarlik(hayvansalVarlik);
            hayvansalVarlik.getHayvansalVarlikGelir().add(hayvansalVarlikGelir);
            hayvansalVarlikGelirRepository.save(hayvansalVarlikGelir);
            hayvansalVarlikRepository.save(hayvansalVarlik);
        }

        List<HayvansalGiderIliskisi> hayvansalGiderIliskisiList = hayvanGiderIliskisiRepository.findByHayvanIrkAndCinsiyet(hayvanIrk, hayvansalVarlikDto.getCinsiyet());
        for (HayvansalGiderIliskisi hayvansalGiderIliskisi : hayvansalGiderIliskisiList) {
            HayvansalVarlikGider hayvansalVarlikGider = new HayvansalVarlikGider();
            HayvansalGider hayvansalGider = hayvansalGiderIliskisi.getHayvansalGider();
            BigDecimal toplamGider = hayvansalGider.getBirimFiyat().multiply(hayvansalGiderIliskisi.getMiktar());
            toplamGider = hayvansalVarlik.getHayvanAdeti().multiply(toplamGider);

            hayvansalVarlikGider.setAd(hayvansalGider.getAd());
            hayvansalVarlikGider.setMiktar(hayvansalGiderIliskisi.getMiktar());
            hayvansalVarlikGider.setToplamFiyat(toplamGider);
            hayvansalVarlikGider.setHayvansalVarlik(hayvansalVarlik);
            hayvansalVarlik.getHayvansalVarlikGider().add(hayvansalVarlikGider);
            hayvansalVarlikGiderRepository.save(hayvansalVarlikGider);
            hayvansalVarlikRepository.save(hayvansalVarlik);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Hayvansal varlık başarılı bir şekilde oluşturuldu");
    }

    @GetMapping("/ciftciler/{id}/varliklar")
    public ResponseEntity<?> irkIdGelir(@PathVariable(name = "id") Long id) {
        List<HayvansalVarlik> hayvansalVarlikList = hayvansalVarlikRepository.findByCiftciVarlikId(id);
        return ResponseEntity.status(HttpStatus.OK).body(hayvansalVarlikList);
    }

    @GetMapping("/ciftci-varlik/{id}/varliklar/{hayvanIrkId}")
    public ResponseEntity<?> irkIdGelir(@PathVariable(name = "id") Long id, @PathVariable(name = "hayvanIrkId") Long hayvanIrkId) {
        CiftciVarlik ciftciVarlik = ciftciVarlikRepository.findCiftciVarlikById(id);
        HayvanIrk hayvanIrk = hayvanIrkRepository.findHayvanIrkById(hayvanIrkId);
        List<HayvansalVarlik> hayvansalVarlikList = hayvansalVarlikRepository.findByCiftciVarlikAndHayvanIrk(ciftciVarlik, hayvanIrk);
        if (hayvansalVarlikList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Elemanlar bulunulamadı");
        }
        return ResponseEntity.status(HttpStatus.OK).body(hayvansalVarlikList);
    }
}