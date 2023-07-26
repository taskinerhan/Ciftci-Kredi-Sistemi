package com.cks.ciftcikredisistemi.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KrediBasvuruDto {
    private long ciftciId;
    private BigDecimal miktar;
}
