package com.cks.ciftcikredisistemi.service;

import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.entity.gayrimenkul.GayrimenkulVarlik;
import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import com.cks.ciftcikredisistemi.enums.GayrimenkulTipi;
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
public class GayrimenkulVarlikService {
    private final KrediYonetimRepository krediYonetimRepository;
    private final WebClient customWebClient;

    @Async("customTaskExecutor")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CompletableFuture<Void> krediBasvuruDeger(Long krediBasvuruId) {
        CompletableFuture<Void> feature = new CompletableFuture<>();
        KrediBasvuru krediBasvuru = krediYonetimRepository.findKrediBasvuruById(krediBasvuruId);

        BigDecimal varlik = BigDecimal.ZERO;
        BigDecimal gayrimenkulVarlikDeger = BigDecimal.ZERO;
        CiftciVarlik ciftciVarlik = krediBasvuru.getCiftciVarlik();
        for (GayrimenkulVarlik gayrimenkulVarlik : ciftciVarlik.getGayrimenkulVarlik()) {
            varlik = varlik.add(gayrimenkulVarlik.getGayrimenkulDegeri());
        }
        GayrimenkulVarlik gayrimenkul = new GayrimenkulVarlik();
        GayrimenkulTipi gayrimenkulTipi = gayrimenkul.getGayrimenkulTipi();
        String resp = customWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/gayrimenkul-varlik-deger-yonetimi/{gayrimenkulTipi}")
                        .build(gayrimenkulTipi))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if (resp != null) {
            gayrimenkulVarlikDeger = new BigDecimal(resp);
            varlik = varlik.add(gayrimenkulVarlikDeger);
        }
        ciftciVarlik.setGayrimenkulToplam(varlik);

        feature.complete(null);

        return feature;
    }
}
