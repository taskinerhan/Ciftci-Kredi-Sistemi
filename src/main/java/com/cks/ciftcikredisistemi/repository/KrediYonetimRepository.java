package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import com.cks.ciftcikredisistemi.enums.KrediDurum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface KrediYonetimRepository extends JpaRepository<KrediBasvuru,Long> {
    KrediBasvuru findKrediBasvuruById(long Id);
    List<KrediBasvuru> findKrediBasvurularByCiftciId(long id);
    List<KrediBasvuru> findKrediBasvurularByCiftciIdAndKrediDurum(long id,KrediDurum krediDurum);

}
