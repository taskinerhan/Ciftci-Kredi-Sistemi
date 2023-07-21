package com.cks.ciftcikredisistemi.entity.hayvan;

import com.cks.ciftcikredisistemi.entity.ciftci.CiftciVarlik;
import jakarta.persistence.*;
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
@Table(name="hayvansal_varlik")
public class HayvansalVarlik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="ciftci_varlik_id", nullable=false)
    private CiftciVarlik ciftciVarlik;
    @ManyToOne
    @JoinColumn(name = "hayvan_irk_id")
    private HayvanIrk hayvanIrk;
    private  BigDecimal hayvanAdeti;
    private  BigDecimal toplamVarlikDegeri;
    @OneToMany(mappedBy="hayvansalVarlik")
    private Set<HayvansalVarlikGelir> hayvansalVarlikGelir;
}
