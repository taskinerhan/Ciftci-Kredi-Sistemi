package com.cks.ciftcikredisistemi.entity.ciftci;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.entity.gayrimenkul.GayrimenkulVarlik;
import com.cks.ciftcikredisistemi.entity.hayvan.HayvansalVarlik;
import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ciftci_varlik")
public class CiftciVarlik extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal gayrimenkulToplam;
    private BigDecimal hayvansalToplam;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ciftci_id")
    private Ciftci ciftci;

    @OneToOne
    @JoinColumn(name = "kredi_basvuru_id")
    private KrediBasvuru krediBasvuru;

    @OneToMany(mappedBy = "ciftciVarlik")
    private Set<GayrimenkulVarlik> gayrimenkulVarlik = new HashSet<>();

    @OneToMany(mappedBy = "ciftciVarlik")
    private Set<HayvansalVarlik> hayvansalVarlik = new HashSet<>();
}
