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
public class HayvansalVarlikDto {
    private Long ciftciVarlikId;

    private Long hayvanIrkId;

    private BigDecimal hayvanAdeti;

    private BigDecimal toplamVarlikDegeri;

    private Cinsiyet cinsiyet;
}
