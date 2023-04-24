package com.darfat.docreaderapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FormPernyataanDTO {

    private String documentTitle;
    private String officerName;
    private String officerPhoneNumber;
    private String officerPosition;
    private String officerDepartment;
    private String kelurahanDesa;
    private String kecamatan;
    private String kabupatenKota;
    private String provinsi;

    private PenerimaBantuanPanganDTO pbp1;
    private PenerimaBantuanPanganDTO pbp2;
    private PenerimaBantuanPanganDTO pbp3;
    private PenerimaBantuanPanganDTO pbp4;
    private PenerimaBantuanPanganDTO pbp5;

    @Getter
    @Setter
    @Builder
    public static class PenerimaBantuanPanganDTO {

        private String pbpTidakDitemukan;
        private String alamatPbpTidakDitemukan;
        private String pbpPengganti;
        private String alamatPbpPengganti;
    }
}
