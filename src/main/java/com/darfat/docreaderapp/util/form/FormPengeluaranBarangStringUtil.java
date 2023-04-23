package com.darfat.docreaderapp.util.form;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.*;
import java.util.Locale;

@Slf4j
public class FormPengeluaranBarangStringUtil {

    private static String NEW_LINE = "\\r?\\n";

    public static String getBranch(String text) {
        return split(text, NEW_LINE, 2);
    }

    public static String getRecipientAddress(String text) {
        return split(text, NEW_LINE, 4, 10);
    }

    public static String getSourceWarehouse(String text) {
        String npwpWithLabel = split(text, NEW_LINE, 12);
        String[] values = npwpWithLabel.split(":");
        return values[1].trim();
    }

    public static String getNpwp(String text) {
        String npwpWithLabel = split(text, NEW_LINE, 11);
        String[] values = npwpWithLabel.split(":");
        return values[1].trim();
    }

    public static String getReference(String text) {
        return split(text, NEW_LINE, 15);
    }

    public static String getDocumentTitle(String text) {
        return split(text, NEW_LINE, 17);
    }

    public static String getDocumentNumber(String text) {
        return split(text, NEW_LINE, 18);
    }

    public static String getSourceDocument(String text) {
        String npwpWithLabel = split(text, NEW_LINE, 13);
        String[] values = npwpWithLabel.split(":");
        return values[1].trim();
    }

    public static String getStatus(String text) {
        return split(text, NEW_LINE, 27);
    }

    public static String getOrderDate(String text) {
        return split(text, NEW_LINE, 32);
    }

    public static String getProductDescription(String text) {
        return split(text, NEW_LINE, 23);
    }

    public static String getSourceLocation(String text) {
        String items = split(text, NEW_LINE, 24);
        try {
            String[] values = items.split(" ");
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i <= 1; i++) {
                buffer.append(values[i]);
                buffer.append(" ");
            }
            return buffer.toString();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Source location index out of bounds");
        }
        return null;
    }

    public static String getLotNo(String text) {
        String items = split(text, NEW_LINE, 24);
        try {
            String[] values = items.split(" ");
            StringBuffer buffer = new StringBuffer();
            for (int i = 2; i <= 3; i++) {
                buffer.append(values[i]);
                buffer.append(" ");
            }
            return buffer.toString();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("lot no index out of bounds");
        }
        return null;
    }

    public static Float getQuantity(String text) {
        String items = split(text, NEW_LINE, 24);
        try {
            String[] values = items.split(" ");

            return parseDecimal(values[4]);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Quantity index out of bounds");
        } catch (ParseException e) {
            log.error("Quantity parse exception");
        }
        return null;
    }

    public static BigDecimal getAmount(String text) {
        String items = split(text, NEW_LINE, 24);
        try {
            String[] values = items.split(" ");
            log.info("Amount values, [{}]",values);
            return parseBigDecimal(values[5]);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Amount index out of bounds");
        }
        return null;
    }

    public static String getSourceDestination(String text) {
        String items = split(text, NEW_LINE, 24);
        try {
            String[] values = items.split(" ");
            StringBuffer buffer = new StringBuffer();
            for (int i = 6; i < values.length; i++) {
                buffer.append(values[i]);
                buffer.append(" ");
            }
            return buffer.toString();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Source destination index out of bounds");
        }
        return null;
    }

    public static String getArmadaName(String text) {
        return split(text, NEW_LINE, 21);
    }

    public static String getArmadaNumber(String text) {
        return split(text, NEW_LINE, 35);
    }

    public static String split(String text, String delimiter, int pos) {
        String[] values = text.split(delimiter);
        return values[pos];
    }

    public static String split(String text, String delimiter, int startPos, int endPos) {
        String[] values = text.split(delimiter);
        StringBuilder buffer = new StringBuilder();
        for (int i = startPos; i <= endPos; i++) {
            buffer.append(values[i]);
            buffer.append(" ");
        }
        return buffer.toString();
    }

    private static Float parseDecimal(String input) throws ParseException {
        Locale in_ID = new Locale("in","ID");

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#.000");
        decimalFormat.setDecimalFormatSymbols(symbols);

        Float result = decimalFormat.parse(input).floatValue();
        return result;
    }
    private static BigDecimal parseBigDecimal(String input){
        Locale in_ID = new Locale("in","ID");

        DecimalFormat nf = (DecimalFormat)NumberFormat.getInstance(in_ID);
        nf.setParseBigDecimal(true);

        return  (BigDecimal)nf.parse(input, new ParsePosition(0));

    }
}
