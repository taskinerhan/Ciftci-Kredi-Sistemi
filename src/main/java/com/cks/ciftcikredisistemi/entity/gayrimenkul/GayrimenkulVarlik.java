package com.cks.ciftcikredisistemi.entity.gayrimenkul;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.cks.ciftcikredisistemi.enums.GayrimenkulTipi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="GayrimenkulVarlik")
public class GayrimenkulVarlik extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ada;
    private String parsel;
    private double yuzOlcumu;
    @Enumerated(EnumType.STRING)
    private GayrimenkulTipi gayrimenkulTipi;
    private BigDecimal gayrimenkulDegeri;
    @ManyToOne
    @JoinColumn(name="ciftci_varlik_id")
    private CiftciVarlik ciftciVarlik;

}
