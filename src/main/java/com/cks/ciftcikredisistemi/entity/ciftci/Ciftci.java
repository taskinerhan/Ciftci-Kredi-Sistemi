package com.cks.ciftcikredisistemi.entity.ciftci;

import com.cks.ciftcikredisistemi.entity.base.BaseEntity;
import com.cks.ciftcikredisistemi.entity.kredi.KrediBasvuru;
import com.cks.ciftcikredisistemi.enums.KrediDurum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Ciftci")
public class Ciftci extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ad;
    private String soyad;
    private String cinsiyet;
    private String tcko;
    private Date dogumTarihi;
    @OneToMany(mappedBy="ciftci")
    private Set<KrediBasvuru> krediBasvuru;
    @OneToMany(mappedBy="ciftci")
    private Set<CiftciVarlik> ciftciVarlik;

}
