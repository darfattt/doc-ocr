package com.darfat.docreaderapp.util.form;

public class FormBASTPBPUtil extends BaseFormUtil {

    public static String getProvinsi(String text) {
        return split(text, NEW_LINE, 28).replaceAll(": ", "");
    }

    public static String getKabupatenKota(String text) {
        return split(text, NEW_LINE, 29).replaceAll(": ", "");
    }

    public static String getKecamatan(String text) {
        return split(text, NEW_LINE, 30).replaceAll(": ", "");
    }

    public static String getKelurahanDesa(String text) {
        return split(text, NEW_LINE, 31).replaceAll(": ", "");
    }

    public static String getDocumentTitle(String text) {
        return split(text, NEW_LINE, 45, 46);
    }

    public static String getDocumentNumber(String text) {
        return split(text, NEW_LINE, 49);
    }

    public static String getPeriod(String text) {
        return split(text, NEW_LINE, 49).replaceAll("Alokasi Bulan / Tahap: ", "");
    }

    public static String getRWRT(String text) {
        return split(text, NEW_LINE, 10).replaceAll("RW/RT ", "");
    }

    public static String getBastNumber(String text) {
        return split(text, NEW_LINE, 11);
    }

    public static String getDocumentDescription(String text) {
        return split(text, NEW_LINE, 12, 13);
    }

    public static String getKCU(String text) {
        return split(text, NEW_LINE, 107);
    }

    public static String getKantorSerah(String text) {
        return split(text, NEW_LINE, 108);
    }
}
