package com.cks.ciftcikredisistemi.entity.hayvan;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.enums.HayvanTuru;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hayvan_irk")
public class HayvanIrk extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ad;

    @Enumerated(EnumType.STRING)
    private HayvanTuru hayvanTuru;

    @OneToOne(mappedBy = "hayvanIrk")
    private HayvansalGiderIliskisi hayvansalGiderIliskisi;

    @OneToOne(mappedBy = "hayvanIrk")
    private HayvanGelirIliskisi hayvanGelirIliskisi;

    @OneToMany(mappedBy = "hayvanIrk")
    private Set<HayvansalVarlik> hayvansalVarlik;
}
