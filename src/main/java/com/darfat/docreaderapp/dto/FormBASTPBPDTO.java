package com.darfat.docreaderapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FormBASTPBPDTO {

    private String documentTitle;
    private String documentNumber;
    private String period;
    private String provinsi;
    private String kabupatenKota;
    private String kecamatan;
    private String kelurahanDesa;
    private String rtRw;
    private String kcu;
    private String kantorSerah;
    private String bastNumber;
    private String documentDescription;

    private PenerimaBantuanPanganDTO pbp1;

    @Getter
    @Setter
    @Builder
    public static class PenerimaBantuanPanganDTO {

        private String nama;
        private String alamat;
        private String nomor;
        private String jumlah;
    }
}
