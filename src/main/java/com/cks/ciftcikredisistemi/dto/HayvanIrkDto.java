package com.cks.ciftcikredisistemi.dto;

import com.cks.ciftcikredisistemi.enums.HayvanTuru;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HayvanIrkDto {

    private String ad;

    @Enumerated(EnumType.STRING)
    private HayvanTuru hayvanTuru;
}
