package com.cks.ciftcikredisistemi.entity.ciftci;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.entity.ciftci.Ciftci;
import com.cks.ciftcikredisistemi.entity.gayrimenkul.GayrimenkulVarlik;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlik;
import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CiftciVarlik")
public class CiftciVarlik extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "ciftci_id")
    private Ciftci ciftci;
    private BigDecimal gayrimenkulToplam;
    private BigDecimal hayvansalToplam;
    @OneToOne
    @JoinColumn(name = "kredi_basvuru_id")
    private KrediBasvuru krediBasvuru;
    @OneToMany(mappedBy = "ciftciVarlik")
    private Set<GayrimenkulVarlik> gayrimenkulVarlikList;
    @OneToMany(mappedBy = "ciftciVarlik")
    private Set<HayvansalVarlik> hayvansalVarlik;



}
