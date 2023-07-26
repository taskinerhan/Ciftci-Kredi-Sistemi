package com.cks.ciftcikredisistemi.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CiftciDto extends CsvBean{
    @CsvBindByName
    private String ad;
    @CsvBindByName
    private String soyad;
    @CsvBindByName
    private String cinsiyet;
    @CsvBindByName
    private String tcko;
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName
    private Date dogumTarihi;
}
