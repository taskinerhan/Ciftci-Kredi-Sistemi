package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.HayvansalGiderIliskisDto;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGider;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGiderIliskisi;
import com.cks.ciftcikredisistemi.enums.Cinsiyet;
import com.cks.ciftcikredisistemi.repository.HayvanGiderIliskisiRepository;
import com.cks.ciftcikredisistemi.repository.HayvanIrkRepository;
import com.cks.ciftcikredisistemi.repository.HayvansalGiderRepository;
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
@RequiredArgsConstructor
@RequestMapping("hayvan-gider-iliskisi-yonetimi")
public class HayvanGiderIliskisiController {
    private final HayvanGiderIliskisiRepository hayvanGiderIliskisiRepository;
    private final HayvansalGiderRepository hayvansalGiderRepository;
    private final HayvanIrkRepository hayvanIrkRepository;

    @PostMapping("")
    public ResponseEntity<?> hayvanGiderIliskisiEkle(@RequestBody HayvansalGiderIliskisDto hayvansalGiderIliskisDto) {
        if (hayvansalGiderIliskisDto == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Girilen bilgiler geçersiz veya eksik.");
        }

        HayvansalGiderIliskisi hayvansalGiderIliskisi = new HayvansalGiderIliskisi();
        hayvansalGiderIliskisi.setCinsiyet(hayvansalGiderIliskisDto.getCinsiyet());
        hayvansalGiderIliskisi.setMiktar(hayvansalGiderIliskisDto.getBigDecimal());

        HayvansalGider hayvansalGider = hayvansalGiderRepository.findHayvansalGiderById(hayvansalGiderIliskisDto.getHayvansalGiderId());
        hayvansalGiderIliskisi.setHayvansalGider(hayvansalGider);

        HayvanIrk hayvanIrk = hayvanIrkRepository.findHayvanIrkById(hayvansalGiderIliskisDto.getHayvanIrkId());
        hayvansalGiderIliskisi.setHayvanIrk(hayvanIrk);

        hayvanGiderIliskisiRepository.save(hayvansalGiderIliskisi);
        return ResponseEntity.status(HttpStatus.OK).body("Başarılı bir şekilde oluşturuldu");
    }

    @GetMapping("/irklar/{id}/giderler")
    public ResponseEntity<?> irkIdGiderList(@PathVariable(name = "id") Long id) {
        HayvanIrk hayvanIrk = hayvanIrkRepository.findHayvanIrkById(id);
        List<HayvansalGiderIliskisi> hayvanGelirIliskisiList = hayvanGiderIliskisiRepository.findByHayvanIrk(hayvanIrk);
        return ResponseEntity.status(HttpStatus.OK).body(hayvanGelirIliskisiList);
    }

    @GetMapping("/irklar/{id}/{cinsiyet}/giderler")
    public ResponseEntity<?> irkIdGider(@PathVariable(name = "id") Long id, @PathVariable(name = "cinsiyet") Cinsiyet cinsiyet) {
        HayvanIrk hayvanIrk = hayvanIrkRepository.findHayvanIrkById(id);
        List<HayvansalGiderIliskisi> hayvanGelirIliskisiList = hayvanGiderIliskisiRepository.findByHayvanIrkAndCinsiyet(hayvanIrk, cinsiyet);
        if (hayvanGelirIliskisiList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Elemanlar bulunulamadı");
        }
        return ResponseEntity.status(HttpStatus.OK).body(hayvanGelirIliskisiList);
    }
}
