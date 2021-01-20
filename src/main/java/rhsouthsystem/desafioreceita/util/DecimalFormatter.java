package rhsouthsystem.desafioreceita.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class DecimalFormatter {
    public static BigDecimal toBigDecimal(String numberToFormat) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "0,00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        try {
            return (BigDecimal) decimalFormat.parse(numberToFormat);
        } catch (ParseException e) {
            System.out.println("Error parsing to BigDecimal");
            return null;
        }
    }
}
