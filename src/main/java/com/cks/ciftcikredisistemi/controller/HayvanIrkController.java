package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.HayvanIrkDto;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvanIrk;
import com.cks.ciftcikredisistemi.repository.HayvanIrkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hayvan-irk-yonetimi")
@RequiredArgsConstructor
public class HayvanIrkController {
    private HayvanIrkRepository hayvanIrkRepository;

    @PostMapping("")
    public ResponseEntity<?> hayvanIrkEkle(@RequestBody HayvanIrkDto hayvanIrkDto) {
        if (hayvanIrkDto == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Hayvan ırk bilgileri geçersiz veya eksik.");
        }
        HayvanIrk hayvanIrk = new HayvanIrk();
        hayvanIrk.setAd(hayvanIrkDto.getAd());
        hayvanIrk.setHayvanTuru(hayvanIrkDto.getHayvanTuru());
        hayvanIrkRepository.save(hayvanIrk);
        return ResponseEntity.status(HttpStatus.OK).body("Başarılı bir şekilde oluşturuldu");
    }

    @GetMapping("irklar")
    public ResponseEntity<?> hayvanIrkListGetir() {
        List<HayvanIrk> hayvanIrkList = hayvanIrkRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(hayvanIrkList);
    }

    @GetMapping("irklar/{id}")
    public ResponseEntity<?> hayvanIrkIdGetir(@PathVariable(name = "id") Long id) {
        HayvanIrk hayvanIrk=hayvanIrkRepository.findHayvanIrkById(id);
        if(hayvanIrk==null){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Hayvan ırk bilgileri geçersiz veya eksik.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(hayvanIrk);
    }


}
