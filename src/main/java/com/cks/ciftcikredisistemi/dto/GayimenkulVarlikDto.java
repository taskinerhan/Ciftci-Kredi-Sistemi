package com.cks.ciftcikredisistemi.dto;

import com.cks.ciftcikredisistemi.enums.GayrimenkulTipi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GayimenkulVarlikDto {
    private Long ciftciVarlikId;

    private String ada;

    private String parsel;

    private double yuzOlcumu;

    private GayrimenkulTipi gayrimenkulTipi;

    private BigDecimal gayrimenkulDegeri;
}
