package com.darfat.docreaderapp.util.form;

public class FormBASTPBPPUtil extends BaseFormUtil {

    public static String getKelurahanDesa(String text) {
        return split(text, NEW_LINE, 30).replaceAll(": ", "");
    }

    public static String getKecamatan(String text) {
        return split(text, NEW_LINE, 29).replaceAll(": ", "");
    }

    public static String getKabupatenKota(String text) {
        return split(text, NEW_LINE, 28).replaceAll(": ", "");
    }

    public static String getProvinsi(String text) {
        return split(text, NEW_LINE, 27).replaceAll(": ", "");
    }

    public static String getDocumentTitle(String text) {
        return split(text, NEW_LINE, 12, 14);
    }
}
