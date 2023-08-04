package com.cks.ciftcikredisistemi.entity.hayvan;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.enums.HayvanTuru;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

    @JsonBackReference
    @OneToOne(mappedBy = "hayvanIrk")
    private HayvansalGiderIliskisi hayvansalGiderIliskisi;

    @JsonBackReference
    @OneToOne(mappedBy = "hayvanIrk")
    private HayvanGelirIliskisi hayvanGelirIliskisi;

    @JsonManagedReference
    @OneToMany(mappedBy = "hayvanIrk")
    private Set<HayvansalVarlik> hayvansalVarlik = new HashSet<>();
}
