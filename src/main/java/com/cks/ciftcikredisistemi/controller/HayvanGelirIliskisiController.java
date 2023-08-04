package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.HayvanGelirIlisikisiDto;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvanGelirIliskisi;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGelir;
import com.cks.ciftcikredisistemi.enums.Cinsiyet;
import com.cks.ciftcikredisistemi.repository.HayvanGelirIliskisiRepository;
import com.cks.ciftcikredisistemi.repository.HayvanIrkRepository;
import com.cks.ciftcikredisistemi.repository.HayvansalGelirRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hayvan-gelir-iliskisi-yonetimi")
@RequiredArgsConstructor
public class HayvanGelirIliskisiController {
    private final HayvanGelirIliskisiRepository hayvanGelirIliskisiRepository;
    private final HayvansalGelirRepository hayvansalGelirRepository;
    private final HayvanIrkRepository hayvanIrkRepository;

    @PostMapping("")
    public ResponseEntity<?> hayvanGelirIliskisiEkle(@RequestBody HayvanGelirIlisikisiDto hayvanGelirIlisikisiDto) {
        if (hayvanGelirIlisikisiDto == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Girilen bilgiler geçersiz veya eksik.");
        }
        HayvanGelirIliskisi hayvanGelirIliskisi = new HayvanGelirIliskisi();
        hayvanGelirIliskisi.setCinsiyet(hayvanGelirIlisikisiDto.getCinsiyet());
        hayvanGelirIliskisi.setMiktar(hayvanGelirIlisikisiDto.getMiktar());

        HayvansalGelir hayvansalGelir = hayvansalGelirRepository.findHayvansalGelirById(hayvanGelirIlisikisiDto.getHayvansalGelirId());
        hayvanGelirIliskisi.setHayvansalGelir(hayvansalGelir);

        HayvanIrk hayvanIrk = hayvanIrkRepository.findHayvanIrkById(hayvanGelirIlisikisiDto.getHayvanIrkId());
        hayvanGelirIliskisi.setHayvanIrk(hayvanIrk);

        hayvanGelirIliskisiRepository.save(hayvanGelirIliskisi);
        return ResponseEntity.status(HttpStatus.OK).body("Başarılı bir şekilde oluşturuldu");
    }

    @GetMapping("irklar/{id}/gelirler")
    public ResponseEntity<?> irkIdGelir(@PathVariable(name = "id") Long id) {
        HayvanIrk hayvanIrk = hayvanIrkRepository.findHayvanIrkById(id);
        List<HayvanGelirIliskisi> hayvanGelirIliskisiList = hayvanGelirIliskisiRepository.findByHayvanIrk(hayvanIrk);
        return ResponseEntity.status(HttpStatus.OK).body(hayvanGelirIliskisiList);
    }

    @GetMapping("irklar/{id}/{cinsiyet}/gelirler")
    public ResponseEntity<?> irkIdGelir(@PathVariable(name = "id") Long id, @PathVariable(name = "cinsiyet") Cinsiyet cinsiyet) {
        HayvanIrk hayvanIrk = hayvanIrkRepository.findHayvanIrkById(id);
        List<HayvanGelirIliskisi> hayvanGelirIliskisiList = hayvanGelirIliskisiRepository.findByHayvanIrkAndCinsiyet(hayvanIrk, cinsiyet);
        if (hayvanGelirIliskisiList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Elemanlar bulunulamadı");
        }
        return ResponseEntity.status(HttpStatus.OK).body(hayvanGelirIliskisiList);
    }
}