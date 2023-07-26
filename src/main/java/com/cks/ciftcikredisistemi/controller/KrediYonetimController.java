package com.cks.ciftcikredisistemi.controller;

import com.cks.ciftcikredisistemi.dto.KrediBasvuruDto;
import com.cks.ciftcikredisistemi.entity.ciftci.Ciftci;
import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import com.cks.ciftcikredisistemi.enums.KrediDurum;
import com.cks.ciftcikredisistemi.repository.CiftciYonetimiRepository;
import com.cks.ciftcikredisistemi.repository.KrediYonetimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cks.ciftcikredisistemi.enums.KrediDurum.*;

@RestController
@RequiredArgsConstructor
public class KrediYonetimController {
    private final KrediYonetimRepository krediYonetimRepository;
    private final CiftciYonetimiRepository ciftciYonetimiRepository;

    @PostMapping("/kredi-yonetimi")
    public ResponseEntity<?> krediBasvuru(@RequestBody KrediBasvuruDto krediBasvuruDto) {
        long ciftciId = krediBasvuruDto.getCiftciId();
        Ciftci ciftci = ciftciYonetimiRepository.findCiftciById(ciftciId);
        if (ciftci == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Çiftçi bulunamadı.");
        }
        KrediBasvuru krediBasvuru = new KrediBasvuru();
        krediBasvuru.setCiftci(ciftci);
        krediBasvuru.setTalepEdilenKrediMiktari(krediBasvuruDto.getMiktar());
        ciftci.getKrediBasvuru().add(krediBasvuru);
        krediYonetimRepository.save(krediBasvuru);
        ciftciYonetimiRepository.save(ciftci);
        return ResponseEntity.status(HttpStatus.CREATED).body("Kredi başvurusu başarılı bir şekilde oluşturuldu");
    }

    @PostMapping("/kredi-yonetimi/{id}/degerlendir")
    public ResponseEntity<?> krediBasvuruDegerlendir(@PathVariable(name = "id") Long id) {
        KrediBasvuru krediBasvuru = krediYonetimRepository.findKrediBasvuruById(id);
        if (krediBasvuru.getKrediDurum() == REDDEDILMIS) {
            krediBasvuru.setKrediDurum(DEGERLENDIRME_BEKLIYOR);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Başvuru DEGERLENDIRME_BEKLIYOR durumuna getirildi");
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Kredi başvurusu bulunamadı.");
    }

    @GetMapping("/kredi-yonetimi/krediler")
    public ResponseEntity<List<KrediBasvuru>> krediler() {
        List<KrediBasvuru> krediBasvuruList = krediYonetimRepository.findAll();
        if (krediBasvuruList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(krediBasvuruList);
    }

    @GetMapping("/kredi-yonetimi/krediler/{id}")
    public ResponseEntity<?> krediBilgisi(@PathVariable(name = "id") Long id) {
        KrediBasvuru krediBasvuru = krediYonetimRepository.findKrediBasvuruById(id);
        if (krediBasvuru == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Kredi başvurusu bulunamadı.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(krediBasvuru);
    }

    @GetMapping("/kredi-yonetimi/ciftciler/{id}")
    public ResponseEntity<?> ciftciBilgisi(@PathVariable(name = "id") Long id) {
        List<KrediBasvuru> krediBasvuruList = krediYonetimRepository.findKrediBasvurularByCiftciId(id);
        if (krediBasvuruList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Kredi başvurusu bulunamadı.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(krediBasvuruList);
    }

    @GetMapping("/kredi-yonetimi/ciftciler/{id}/{krediDurum}")
    public ResponseEntity<?> ciftciKrediDurumuBilgisi(@PathVariable(name = "id") Long id,
                                                      @PathVariable(name = "krediDurum") KrediDurum krediDurum) {
        List<KrediBasvuru> ciftciKrediBasvurulari = krediYonetimRepository.findKrediBasvurularByCiftciIdAndKrediDurum(id, krediDurum);

        if (ciftciKrediBasvurulari.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Belirtilen çiftçi ve kredi durumuna ait kredi başvurusu bulunamadı.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(ciftciKrediBasvurulari);
    }
}
