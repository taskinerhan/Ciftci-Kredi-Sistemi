package com.cks.ciftcikredisistemi.dto;

import com.cks.ciftcikredisistemi.enums.Birim;
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
public class HayvansalGelirDto {
    private String ad;

    @Enumerated(EnumType.STRING)
    private Birim birim;
    private BigDecimal birimFiyat;
}
