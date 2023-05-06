package com.darfat.docreaderapp.constants;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum DocumentsTypeEnum {
    SURAT_KELUAR("001","DOKUMEN PENGELUARAN BARANG"),
    SURAT_JALAN("002","SURAT JALAN");

    private String code;
    private String value;
    DocumentsTypeEnum(String code,String value){
        this.code = code;
        this.value = value;
    }
}
