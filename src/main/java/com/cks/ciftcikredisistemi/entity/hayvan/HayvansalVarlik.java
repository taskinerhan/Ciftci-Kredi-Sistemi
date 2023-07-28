package com.cks.ciftcikredisistemi.entity.hayvan;

import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hayvansal_varlik")
public class HayvansalVarlik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal hayvanAdeti;
    private BigDecimal toplamVarlikDegeri;

    @ManyToOne
    @JoinColumn(name = "ciftci_varlik_id", nullable = false)
    private CiftciVarlik ciftciVarlik;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hayvan_irk_id")
    private HayvanIrk hayvanIrk;

    @OneToMany(mappedBy = "hayvansalVarlik")
    private Set<HayvansalVarlikGelir> hayvansalVarlikGelir;
}
