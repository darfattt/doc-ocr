package com.darfat.docreaderapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class FormPengeluaranBarangDTO {

    private String branch;
    private String documentTitle;
    private String documentNumber;
    private String recipientAddress;
    private String npwp;
    private String warehouseSource;
    private String documentSource;
    private String reference;
    private String orderStatus;
    private String date;
    private String productDescription;
    private String sourceLocation;
    private String lotNo;
    private Float quantity;
    private BigDecimal amount;
    private String sourceDestination;
    private String armadaName;
    private String armadaNumber;
}
