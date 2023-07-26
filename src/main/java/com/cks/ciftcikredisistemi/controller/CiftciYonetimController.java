package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.CiftciDto;
import com.cks.ciftcikredisistemi.dto.CsvBean;
import com.cks.ciftcikredisistemi.entity.ciftci.Ciftci;
import com.cks.ciftcikredisistemi.repository.CiftciYonetimiRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CiftciYonetimController {

    private final CiftciYonetimiRepository ciftciYonetimiRepository;

    @PostMapping("ciftci-yonetimi")
    public ResponseEntity<?> ciftciEkle(@RequestBody CiftciDto ciftciDto) {
        if (ciftciDto == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Çiftçi bilgileri geçersiz veya eksik.");
        }
        Ciftci ciftci = new Ciftci();
        ciftci.setAd(ciftciDto.getAd());
        ciftci.setSoyad(ciftciDto.getSoyad());
        ciftci.setCinsiyet(ciftciDto.getCinsiyet());
        ciftci.setTcko(ciftciDto.getTcko());
        ciftci.setDogumTarihi(ciftciDto.getDogumTarihi());
        ciftciYonetimiRepository.save(ciftci);
        return ResponseEntity.status(HttpStatus.CREATED).body("Çiftçi eklendi");
    }

    @SneakyThrows
    @PostMapping( value = "/ciftci-yonetimi/yukle2")
    public ResponseEntity<?> ciftciList2() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Başarılı bir şekilde oluşturuldu");
    }

    @SneakyThrows
    @PostMapping(value = "/ciftci-yonetimi/yukle", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ciftciList(@RequestParam(name = "csvFile") MultipartFile csvFile) {
        List<CsvBean> ciftciList;
        try (Reader reader = new InputStreamReader(csvFile.getInputStream())) {
            CsvToBean<CsvBean> cb = new CsvToBeanBuilder<CsvBean>(reader)
                    .withType(CsvBean.class)
                    .build();
            ciftciList = cb.parse();
        }
        ciftciYonetimiRepository.saveAll(ciftciList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Başarılı bir şekilde oluşturuldu");
    }

    @GetMapping("/ciftci-yonetimi/ciftciler")
    public ResponseEntity<List<Ciftci>> ciftciListGetir() {
        List<Ciftci> ciftciList = ciftciYonetimiRepository.findAll();
        if (ciftciList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ciftciList);
    }

    @GetMapping("/ciftci-yonetimi/ciftciler/{id}")
    public ResponseEntity<?> ciftciGetir(@PathVariable(name = "id") Long id) {
        Ciftci ciftci = ciftciYonetimiRepository.findCiftciById(id);
        if (ciftci == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Çiftçi bilgileri geçersiz veya eksik.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ciftci);
    }

}
