package com.darfat.docreaderapp.constants;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum DocumentsTypeEnum {
    SURAT_KELUAR("001","DOKUMEN PENGELUARAN BARANG"),
    SURAT_JALAN("002","SURAT JALAN"),
    SURAT_PERNYATAAN("003","SURAT PERNYATAAN TANGGUNG JAWAB"),
    SURAT_BAST_PENGGANTI("004","BANTUAN PANGAN CADANGAN"),
    SURAT_BAST("005","PENERIMA BANTUAN PANGAN - CBP");

    private String code;
    private String value;
    DocumentsTypeEnum(String code,String value){
        this.code = code;
        this.value = value;
    }

    public static DocumentsTypeEnum fromCode(String code) {
        for (DocumentsTypeEnum a : DocumentsTypeEnum.values()) {
            if (a.getCode().equalsIgnoreCase(code)) {
                return a;
            }
        }
        return null;
    }

    public static DocumentsTypeEnum containText(String text) {
        for (DocumentsTypeEnum a : DocumentsTypeEnum.values()) {
            if (text.contains(a.getValue())) {
                return a;
            }
        }
        return null;
    }
}
