package com.darfat.docreaderapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private String status;
    private String date;
    private String productDescription;
    private String sourceLocation;
    private String lotNo;
    private String quantity;
    private String amount;
    private String sourceDestination;
    private String armadaName;
    private String armadaNumber;
}
