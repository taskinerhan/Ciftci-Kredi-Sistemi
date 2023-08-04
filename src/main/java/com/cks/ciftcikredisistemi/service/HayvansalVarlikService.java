package com.cks.ciftcikredisistemi.service;

import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlik;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlikGelir;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlikGider;
import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import com.cks.ciftcikredisistemi.repository.KrediYonetimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class HayvansalVarlikService {
    private final WebClient customWebClient;
    private final KrediYonetimRepository krediYonetimRepository;

    @Async("customTaskExecutor")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CompletableFuture<Void> hayvanVarlikHesapla(Long krediBasvuruId) {
        CompletableFuture<Void> feature = new CompletableFuture<>();
        try {
            KrediBasvuru krediBasvuru = krediYonetimRepository.findKrediBasvuruById(krediBasvuruId);

            CiftciVarlik ciftciVarlik = krediBasvuru.getCiftciVarlik();
            BigDecimal hayvansalVarlikDeger = BigDecimal.ZERO;
            BigDecimal gelirVarlikDeger = BigDecimal.ZERO;
            for (HayvansalVarlik hayvansalVarlik : ciftciVarlik.getHayvansalVarlik()) {
                BigDecimal gelir = BigDecimal.ZERO;
                BigDecimal gider = BigDecimal.ZERO;

                for (HayvansalVarlikGelir hayvansalVarlikGelir : hayvansalVarlik.getHayvansalVarlikGelir()) {
                    gelir = gelir.add(hayvansalVarlikGelir.getToplamFiyat());
                }
                for (HayvansalVarlikGider hayvansalVarlikGider : hayvansalVarlik.getHayvansalVarlikGider()) {
                    gider = gider.add(hayvansalVarlikGider.getToplamFiyat());
                }

                String resp = customWebClient
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path("hayvansal-varlik-deger-yonetimi/{hayvanIrkId}/{cinsiyet}")
                                .build(hayvansalVarlik.getHayvanIrk().getId(), hayvansalVarlik.getCinsiyet()))
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                if (resp != null) {
                    hayvansalVarlikDeger = new BigDecimal(resp);
                    gelirVarlikDeger = gelirVarlikDeger.add(hayvansalVarlikDeger);
                }
                gelir = gelir.subtract(gider);


                hayvansalVarlik.setToplamVarlikDegeri(gelir);
                gelirVarlikDeger = gelirVarlikDeger.add(gelir);

            }
            ciftciVarlik.setHayvansalToplam(gelirVarlikDeger);
        } catch (Exception e) {
            feature.completeExceptionally(e);
        }

        feature.complete(null);

        return feature;
    }
}
