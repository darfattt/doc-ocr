package com.darfat.docreaderapp.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FormSuratJalanDTO {

    private String branch;
    private String documentTitle;
    private String documentNumber;
    private String recipientAddress;
    private String npwp;
    private String warehouseSource;
    private String reference;
    private String documentSource;
    private String date;
    private String productDescription;
    private Float quantity;
    private BigDecimal amount;
    private String notes;
    private String armadaNumber;
    private String containerNumber;
}
