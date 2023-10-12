package com.metalsa.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author APOOD9272
 */
public class Utilerias {

    public static enum DATE_FORMAT {

        DD_MM_YYYY("dd/MM/yyyy"),
        MM_DD_YYYY("MM/dd/yyyy"),
        YYYY_MM_DD("yyyy/MM/dd");

        private final String formato;

        DATE_FORMAT(String formato) {
            this.formato = formato;
        }

    }

    public static String GET_SYSDATE(DATE_FORMAT format) {
        Date sysdate = new Date();
        return GET_FORMATTED_DATE(sysdate, format);
    }

    public static String GET_DATE_DD_MM_YYYY(Date date) {
        return GET_FORMATTED_DATE(date, DATE_FORMAT.DD_MM_YYYY);
    }

    public static String GET_DATE_MM_DD_YYYY(Date date) {
        return GET_FORMATTED_DATE(date, DATE_FORMAT.MM_DD_YYYY);
    }

    public static String GET_DATE_YYYY_MM_DD(Date date) {
        return GET_FORMATTED_DATE(date, DATE_FORMAT.YYYY_MM_DD);
    }

    public static String GET_FORMATTED_DATE(Date date, DATE_FORMAT format) {
        SimpleDateFormat formater = new SimpleDateFormat(format.formato);
        return formater.format(date);
    }
}
