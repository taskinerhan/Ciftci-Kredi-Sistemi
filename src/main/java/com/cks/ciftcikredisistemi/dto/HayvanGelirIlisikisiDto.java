package com.cks.ciftcikredisistemi.dto;

import com.cks.ciftcikredisistemi.enums.Cinsiyet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HayvanGelirIlisikisiDto {
    private Long hayvanIrkId;

    private Long hayvansalGelirId;

    private Cinsiyet cinsiyet;

    private BigDecimal bigDecimal;
}
