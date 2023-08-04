package com.cks.ciftcikredisistemi.cronjob;

import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import com.cks.ciftcikredisistemi.repository.KrediYonetimRepository;
import com.cks.ciftcikredisistemi.service.GayrimenkulVarlikService;
import com.cks.ciftcikredisistemi.service.HayvansalVarlikService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.cks.ciftcikredisistemi.enums.KrediDurum.DEGERLENDIRME_BEKLIYOR;
import static com.cks.ciftcikredisistemi.enums.KrediDurum.KREDILESMIS;
import static com.cks.ciftcikredisistemi.enums.KrediDurum.REDDEDILMIS;

@Component
@RequiredArgsConstructor
public class HayvansalVarlikCronjob {
    private final KrediYonetimRepository krediYonetimRepository;
    private final HayvansalVarlikService hayvansalVarlikService;
    private final GayrimenkulVarlikService gayrimenkulVarlikService;

    @Scheduled(cron = "0 * * * * *")
    public void krediBasvuruDegerlendir() {

        List<KrediBasvuru> krediBasvuruList = krediYonetimRepository.findAll();
        for (KrediBasvuru krediBasvuru : krediBasvuruList) {


            if (krediBasvuru.getKrediDurum() == DEGERLENDIRME_BEKLIYOR) {
                List<CompletableFuture<Void>> futureList = new ArrayList<>();

                CompletableFuture<Void> hayvanVarlikFuture = hayvansalVarlikService.hayvanVarlikHesapla(krediBasvuru.getId());
                CompletableFuture<Void> gayrimenkulVarlikFuture = gayrimenkulVarlikService.krediBasvuruDeger(krediBasvuru.getId());

                futureList.add(hayvanVarlikFuture);
                futureList.add(gayrimenkulVarlikFuture);

                try {
                    CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                CiftciVarlik ciftciVarlik = krediBasvuru.getCiftciVarlik();
                BigDecimal varlikToplam = ciftciVarlik.getGayrimenkulToplam().add(ciftciVarlik.getHayvansalToplam());

                int varlikDeger = varlikToplam.compareTo(krediBasvuru.getTalepEdilenKrediMiktari());
                if (varlikDeger > 0) {
                    krediBasvuru.setKrediDurum(KREDILESMIS);
                } else {
                    krediBasvuru.setKrediDurum(REDDEDILMIS);
                }
            }
        }

    }

}
