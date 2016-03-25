package tpv.util;

import org.apache.commons.lang3.text.WordUtils;
import org.joda.money.Money;
import org.joda.money.format.MoneyAmountStyle;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String toDisplayCase(String s) {

        final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the character following
        // to be capitalized

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : s.toCharArray()) {
            c = (capNext)
                    ? Character.toUpperCase(c)
                    : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed
        }
        return sb.toString();
    }

    /**
     * Removes all characters from 'str' except letters and numbers
     *
     * @param str The original string
     * @return cleanStr Then string with removed characters
     */
    public static String cleanSpecialCharacters(String str) {
        String result = null;
        
        if(StringUtils.isNotBlank(str)) {
            result = str.replaceAll("[^a-zA-Z0-9]", "");
        }

        return result;
    }

    public static String capitalizeSentence(String content) {
        Pattern capitalize = Pattern.compile("([\\?!\\.\\r?\\n]\\s*)([a-z])");
        Matcher m = capitalize.matcher(content);

        while (m.find()) {
            content = m.replaceFirst(m.group(1) + m.group(2).toUpperCase());
            m = capitalize.matcher(content);
        }

        // Capitalize the first letter of the string.
        content = String.format("%s%s", Character.toUpperCase(content.charAt(0)), content.substring(1));

        return content;
    }

    public static String capitalize(String str) {
        String result = org.apache.commons.lang3.StringUtils.capitalize(str);
        if (result.charAt(1) == '\'') {
            result = new StringBuilder(str.length())
                    .append(str.charAt(0))
                    .append(str.charAt(1))
                    .append(Character.toTitleCase(str.charAt(2)))
                    .append(str.substring(3))
                    .toString();
        }

        return result;
    }

    public static String capitalizeWords(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }

        return WordUtils.capitalize(str, ' ', '\'', '.');
    }

    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (char delimiter : delimiters) {
            if (ch == delimiter) {
                return true;
            }
        }
        return false;
    }

    /**
     * Formats the number in locale ES with a minimum of zero decimals and a maximum of two decimals
     *
     * @param number
     * @return
     */
    public static String formatDecimal(Float number) {
        return formatDecimal(number, 0, 2, "ES");
    }

    /**
     * Formats the number in locale ES. The minimum and maximum number of decimals are the same
     *
     * @param number
     * @param decimals
     * @return
     */
    public static String formatDecimal(Float number, int decimals) {
        return formatDecimal(number, decimals, decimals, "ES");
    }

    /**
     * Formats a number with a given number of minimum and maximum decimals in a given Locale
     *
     * @param number
     * @param minDecimals
     * @param maxDecimals
     * @param locale
     * @return
     */
    public static String formatDecimal(Float number, int minDecimals, int maxDecimals, String locale) {
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale(locale));
        final DecimalFormat decimalFormat = (DecimalFormat) numberFormat;

        numberFormat.setMinimumFractionDigits(minDecimals);
        numberFormat.setMaximumFractionDigits(maxDecimals);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);

        return decimalFormat.format(number);
    }

    public static String formatMoney(Money amount) {
        MoneyFormatter moneyFormatter = new MoneyFormatterBuilder()
                .appendAmount(MoneyAmountStyle.LOCALIZED_GROUPING)
                .appendLiteral(" €")
                .toFormatter();
        String returnValue = moneyFormatter.print(amount);
        return returnValue;
    }

    /**
     * @param value Example 20150430
     * @param format Example yyyyMMdd... same as java.text.SimpleDateFormat
     * @return date or null
     * @exception Exception
     */
    public static Date stringToDate(String value, String format) {

        Date date = null;

        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  date;
    }
}
