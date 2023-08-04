package com.cks.ciftcikredisistemi.entity.ciftci;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ciftci")
public class Ciftci extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ad;
    private String soyad;
    private String cinsiyet;
    private String tcko;
    private Date dogumTarihi;

    @JsonManagedReference
    @OneToMany(mappedBy = "ciftci")
    private Set<KrediBasvuru> krediBasvuru = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "ciftci")
    private Set<CiftciVarlik> ciftciVarlik = new HashSet<>();
}