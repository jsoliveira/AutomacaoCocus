package br.com.cocus.automacaococus.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jsoliveira
 */
public class ConverterDate {

    private static final SimpleDateFormat dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    public static String dateTimeToStr(Date d) {

        try {

            return dateTime.format(d);
        } catch (Exception e) {
            return null;
        }

    }

    public static String dateToStr(Date d) {

        try {

            return date.format(d);
        } catch (Exception e) {
            return null;
        }

    }

    public static Date strToDateTime(String d) {

        try {
            return dateTime.parse(d);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date strToDate(String d) {

        try {
            return date.parse(d);
        } catch (Exception e) {
            return null;
        }
    }

}
