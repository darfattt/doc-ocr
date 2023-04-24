package com.darfat.docreaderapp.util.form;

import java.math.BigDecimal;
import java.text.ParseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormSuratJalanUtil extends BaseFormUtil {

    public static String getBranch(String text) {
        return split(text, NEW_LINE, 1);
    }

    public static String getRecipientAddress(String text) {
        return split(text, NEW_LINE, 3, 9);
    }

    public static String getSourceWarehouse(String text) {
        String npwpWithLabel = split(text, NEW_LINE, 11);
        String[] values = npwpWithLabel.split(":");
        try {
            return values[1].trim();
        } catch (IndexOutOfBoundsException iex) {
            log.error(iex.getMessage());
        }
        return null;
    }

    public static String getNpwp(String text) {
        String npwpWithLabel = split(text, NEW_LINE, 10);
        String[] values = npwpWithLabel.split(":");
        try {
            return values[1].trim();
        } catch (IndexOutOfBoundsException iex) {
            log.error(iex.getMessage());
        }
        return null;
    }

    public static String getReference(String text) {
        String npwpWithLabel = split(text, NEW_LINE, 12);
        String[] values = npwpWithLabel.split(":");
        try {
            return values[1].trim();
        } catch (IndexOutOfBoundsException iex) {
            log.error(iex.getMessage());
        }
        return null;
    }

    public static String getDocumentTitle(String text) {
        return split(text, NEW_LINE, 16);
    }

    public static String getDocumentNumber(String text) {
        return split(text, NEW_LINE, 17);
    }

    public static String getSourceDocument(String text) {
        return split(text, NEW_LINE, 14);
    }

    public static String getOrderDate(String text) {
        return split(text, NEW_LINE, 26);
    }

    public static String getProductDescription(String text) {
        return split(text, NEW_LINE, 19);
    }

    public static Float getQuantity(String text) {
        String items = split(text, NEW_LINE, 29);
        try {
            String[] values = items.split(" ");

            return parseDecimal(values[0]);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Quantity index out of bounds");
        } catch (ParseException e) {
            log.error("Quantity parse exception");
        }
        return null;
    }

    public static BigDecimal getAmount(String text) {
        String items = split(text, NEW_LINE, 29);
        try {
            String[] values = items.split(" ");
            return parseBigDecimal(values[4]);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Amount index out of bounds");
        }
        return null;
    }

    public static String getNotes(String text) {
        String items = split(text, NEW_LINE, 20);
        try {
            String[] labelAndValues = items.split(":");
            String[] values = labelAndValues[1].split("/");
            return values[0];
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Amount index out of bounds");
        }
        return null;
    }

    public static String getArmadaNumber(String text) {
        String items = split(text, NEW_LINE, 20);
        try {
            String[] labelAndValues = items.split(":");
            String[] values = labelAndValues[1].split("/");
            return values[1];
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Amount index out of bounds");
        }
        return null;
    }

    public static String getContainerNo(String text) {
        String items = split(text, NEW_LINE, 20);
        try {
            String[] labelAndValues = items.split(":");
            String[] values = labelAndValues[1].split("/");
            return values[2];
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            log.error("Amount index out of bounds");
        }
        return null;
    }
}
