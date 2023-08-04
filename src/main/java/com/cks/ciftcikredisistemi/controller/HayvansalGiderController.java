package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.HayvansalGiderDto;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalGider;
import com.cks.ciftcikredisistemi.repository.HayvansalGiderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HayvansalGiderController {
    private final HayvansalGiderRepository hayvansalGiderRepository;

    @PostMapping("hayvansal-gider-yonetimi")
    public ResponseEntity<?> hayvanGiderEkle(@RequestBody HayvansalGiderDto hayvansalGiderDto) {
        if (hayvansalGiderDto == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Girilen bilgiler geçersiz veya eksik.");
        }
        HayvansalGider hayvansalGider = new HayvansalGider();
        hayvansalGider.setAd(hayvansalGiderDto.getAd());
        hayvansalGider.setBirim(hayvansalGiderDto.getBirim());
        hayvansalGider.setBirimFiyat(hayvansalGiderDto.getBirimFiyat());
        hayvansalGiderRepository.save(hayvansalGider);

        return ResponseEntity.status(HttpStatus.OK).body("Başarılı bir şekilde oluşturuldu");
    }

    @GetMapping("giderler ")
    public ResponseEntity<?> hayvanGiderListGetir() {
        List<HayvansalGider> hayvansalGiderList = hayvansalGiderRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(hayvansalGiderList);
    }

    @GetMapping("giderler/{id}")
    public ResponseEntity<?> hayvanGiderIdGetir(@PathVariable(name = "id") Long id) {
        HayvansalGider hayvansalGider = hayvansalGiderRepository.findHayvansalGiderById(id);
        if (hayvansalGider == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Girilen bilgiler geçersiz veya eksik.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(hayvansalGider);
    }
}

