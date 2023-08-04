package com.cks.ciftcikredisistemi.dto;

import com.cks.ciftcikredisistemi.enums.KrediDurum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KrediBasvuruDto {
    private long ciftciId;
    private BigDecimal miktar;
    private KrediDurum krediDurum;
}
