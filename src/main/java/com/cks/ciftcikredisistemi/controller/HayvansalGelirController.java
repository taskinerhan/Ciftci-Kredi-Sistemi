package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.HayvansalGelirDto;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGelir;
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
@RequestMapping("hayvansal-gelir-yonetimi")
@RequiredArgsConstructor
public class HayvansalGelirController {
    private final HayvansalGelirRepository hayvansalGelirRepository;

    @PostMapping("")
    public ResponseEntity<?> hayvanIrkEkle(@RequestBody HayvansalGelirDto hayvansalGelirDto) {
        if (hayvansalGelirDto == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Girilen bilgiler geçersiz veya eksik.");
        }
        HayvansalGelir hayvansalGelir = new HayvansalGelir();
        hayvansalGelir.setAd(hayvansalGelirDto.getAd());
        hayvansalGelir.setBirim(hayvansalGelirDto.getBirim());
        hayvansalGelir.setBirimFiyat(hayvansalGelirDto.getBirimFiyat());
        hayvansalGelirRepository.save(hayvansalGelir);

        return ResponseEntity.status(HttpStatus.OK).body("Başarılı bir şekilde oluşturuldu");
    }

    @GetMapping("gelirler ")
    public ResponseEntity<?> hayvanIrkListGetir() {
        List<HayvansalGelir> hayvanIrkList = hayvansalGelirRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(hayvanIrkList);
    }

    @GetMapping("gelirler/{id}")
    public ResponseEntity<?> hayvanIrkIdGetir(@PathVariable(name = "id") Long id) {
        HayvansalGelir hayvansalGelir = hayvansalGelirRepository.findHayvansalGelirById(id);
        if (hayvansalGelir == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Girilen bilgiler geçersiz veya eksik.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(hayvansalGelir);
    }


}
