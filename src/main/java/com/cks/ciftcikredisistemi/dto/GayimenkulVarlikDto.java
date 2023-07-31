package com.cks.ciftcikredisistemi.dto;

import com.cks.ciftcikredisistemi.enums.GayrimenkulTipi;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private GayrimenkulTipi gayrimenkulTipi;

    private BigDecimal gayrimenkulDegeri;
}
