package com.cks.ciftcikredisistemi.entity.kredi;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.entity.ciftci.Ciftci;
import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.enums.KrediDurum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kredi_basvuru")
public class KrediBasvuru extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private KrediDurum krediDurum;

    private Date basvuruTarihi;
    private BigDecimal talepEdilenKrediMiktari;

    @OneToOne(mappedBy = "krediBasvuru")
    private CiftciVarlik ciftciVarlik;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ciftci_id")
    private Ciftci ciftci;
}
