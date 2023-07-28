package com.cks.ciftcikredisistemi.dto;

import com.cks.ciftcikredisistemi.enums.Cinsiyet;
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
public class HayvansalGiderIliskisDto {
    private Long hayvanIrkId;

    private Long hayvansalGiderId;

    @Enumerated(EnumType.STRING)
    private Cinsiyet cinsiyet;

    private BigDecimal bigDecimal;
}
