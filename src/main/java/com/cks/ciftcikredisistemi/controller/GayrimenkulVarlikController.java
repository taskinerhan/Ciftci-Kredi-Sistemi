package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.GayimenkulVarlikDto;
import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.entity.gayrimenkul.GayrimenkulVarlik;
import com.cks.ciftcikredisistemi.enums.GayrimenkulTipi;
import com.cks.ciftcikredisistemi.repository.CiftciVarlikRepository;
import com.cks.ciftcikredisistemi.repository.GayrimenkulVarlikRepository;
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

public class GayrimenkulVarlikController {
    private final CiftciVarlikRepository ciftciVarlikRepository;
    private final GayrimenkulVarlikRepository gayrimenkulVarlikRepository;

    @PostMapping("gayrimenkul-varlik-yonetimi")
    public ResponseEntity<?> gayrimenkulVarlikEkle(@RequestBody GayimenkulVarlikDto gayimenkulVarlikDto) {
        if (gayimenkulVarlikDto == null) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Girilen bilgiler geçersiz veya eksik.");
        }
        GayrimenkulVarlik gayrimenkulVarlik = new GayrimenkulVarlik();
        gayrimenkulVarlik.setAda(gayimenkulVarlikDto.getAda());
        gayrimenkulVarlik.setGayrimenkulDegeri(gayimenkulVarlikDto.getGayrimenkulDegeri());
        gayrimenkulVarlik.setGayrimenkulTipi(gayimenkulVarlikDto.getGayrimenkulTipi());
        gayrimenkulVarlik.setParsel(gayimenkulVarlikDto.getParsel());
        gayrimenkulVarlik.setYuzOlcumu(gayimenkulVarlikDto.getYuzOlcumu());

        CiftciVarlik ciftciVarlik = ciftciVarlikRepository.findCiftciVarlikById(gayimenkulVarlikDto.getCiftciVarlikId());
        gayrimenkulVarlik.setCiftciVarlik(ciftciVarlik);
        gayrimenkulVarlikRepository.save(gayrimenkulVarlik);

        return ResponseEntity.status(HttpStatus.OK).body("Gayrimenkul varlık başarılı bir şekilde oluşturuldu");
    }

    @GetMapping("ciftciler/{id}/varliklar")
    public ResponseEntity<?> gayrimenkulVarlikList(@PathVariable(name = "id") Long id) {
        List<GayrimenkulVarlik> gayrimenkulVarlikList = gayrimenkulVarlikRepository.findByCiftciVarlikId(id);
        return ResponseEntity.status(HttpStatus.OK).body(gayrimenkulVarlikList);
    }

    @GetMapping("{id}/varliklar/{gayrimenkulTip}")
    public ResponseEntity<?> gayrimenkulVarlikGetir(@PathVariable(name = "id") Long id, @PathVariable(name = "gayrimenkulTip") GayrimenkulTipi gayrimenkulTipi) {
        CiftciVarlik ciftciVarlik = ciftciVarlikRepository.findCiftciVarlikById(id);
        List<GayrimenkulVarlik> gayrimenkulVarlikList = gayrimenkulVarlikRepository.findByCiftciVarlikAndGayrimenkulTipi(ciftciVarlik, gayrimenkulTipi);
        if (gayrimenkulVarlikList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Elemanlar bulunulamadı");
        }
        return ResponseEntity.status(HttpStatus.OK).body(gayrimenkulVarlikList);
    }
}
