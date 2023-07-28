package com.cks.ciftcikredisistemi.entity.hayvan;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.enums.Cinsiyet;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "hayvan_gelir_iliskisi")
public class HayvanGelirIliskisi extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Cinsiyet cinsiyet;

    private BigDecimal bigDecimal;

    @OneToOne
    @JoinColumn(name = "hayvansal_gelir_id")
    private HayvansalGelir hayvansalGelir;
    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "hayvan_irk_id")
    private HayvanIrk hayvanIrk;

    @OneToOne
    @JoinColumn(name = "hayvansal_gider_id")
    private HayvansalGider hayvansalGider;
}
