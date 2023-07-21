package com.cks.ciftcikredisistemi.entity.hayvan;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.enums.Birim;
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
@Table(name="hayvansal_varlik_gider")
public class HayvansalVarlikGider extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ad;
    @Enumerated(EnumType.STRING)
    private Birim birim;
    private BigDecimal miktar;
    private BigDecimal toplamFiyat;
    @OneToOne
    @JoinColumn(name = "hayvansal_gider_id")
    private HayvansalGider hayvansalGider;
    @ManyToOne
    @JoinColumn(name = "hayvansal_varlik_id")
    private HayvansalVarlik hayvansalVarlik;

}
