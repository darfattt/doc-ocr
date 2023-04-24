package com.darfat.docreaderapp.util.form;

import java.math.BigDecimal;
import java.text.*;
import java.util.Locale;

public class BaseFormUtil {

    protected static String NEW_LINE = "\\r?\\n";

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

    protected static Float parseDecimal(String input) throws ParseException {
        Locale in_ID = new Locale("in", "ID");

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#.000");
        decimalFormat.setDecimalFormatSymbols(symbols);

        Float result = decimalFormat.parse(input).floatValue();
        return result;
    }

    protected static BigDecimal parseBigDecimal(String input) {
        Locale in_ID = new Locale("in", "ID");

        DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(in_ID);
        nf.setParseBigDecimal(true);

        return (BigDecimal) nf.parse(input, new ParsePosition(0));
    }
}
