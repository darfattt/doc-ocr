package com.darfat.docreaderapp.util.form;

public class FormPernyataanUtil extends BaseFormUtil {

    public static String getOfficerName(String text) {
        return split(text, NEW_LINE, 22).replaceAll(": ", "");
    }

    public static String getOfficerPhoneNumber(String text) {
        return split(text, NEW_LINE, 23).replaceAll(": ", "");
    }

    public static String getOfficerPosition(String text) {
        return split(text, NEW_LINE, 24).replaceAll(": ", "");
    }

    public static String getOfficerDepartment(String text) {
        return split(text, NEW_LINE, 25).replaceAll(": ", "");
    }

    public static String getKelurahanDesa(String text) {
        return split(text, NEW_LINE, 37).replaceAll(": ", "");
    }

    public static String getKecamatan(String text) {
        return split(text, NEW_LINE, 38).replaceAll(": ", "");
    }

    public static String getKabupatenKota(String text) {
        return split(text, NEW_LINE, 39).replaceAll(": ", "");
    }

    public static String getProvinsi(String text) {
        return split(text, NEW_LINE, 40).replaceAll(": ", "");
    }

    public static String getDocumentTitle(String text) {
        return split(text, NEW_LINE, 19, 20);
    }
}
