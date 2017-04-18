package com.spauny.joy.roboscript.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author iulian.dafinoiu
 */
public class DateUtils {

    public static final DateTimeFormatter LONG_FORMAT_DATETIME = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    public static final DateFormat LONG_SIMPLE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");//2014-05-08T21:39:48.739

    public static int compareTwoStringDates(String firstDate, String secondDate) {
        try {
            Date o1Date = LONG_SIMPLE_FORMAT.parse(firstDate);
            Date o2Date = LONG_SIMPLE_FORMAT.parse(secondDate);
            return o1Date.compareTo(o2Date);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static boolean isDateBeforeNowMinusWeeks(String dateToBeChecked, int nrOfWeeks) {
        DateTime lastUpdateDate = DateTime.parse(dateToBeChecked, LONG_FORMAT_DATETIME);
        return DateTime.now().minusWeeks(nrOfWeeks).isBefore(lastUpdateDate);
    }
    
    public static boolean isDateBeforeNowMinus1Week(String dateToBeChecked) {
        return isDateBeforeNowMinusWeeks(dateToBeChecked, 1);
    }
    
    public static boolean isDateBeforeNowMinusDays(String dateToBeChecked, int nrOfDays) {
        DateTime lastUpdateDate = DateTime.parse(dateToBeChecked, LONG_FORMAT_DATETIME);
        return DateTime.now().minusDays(nrOfDays).isBefore(lastUpdateDate);
    }
    
    public static boolean isDateBeforeNowMinus1Day(String dateToBeChecked) {
        return isDateBeforeNowMinusDays(dateToBeChecked, 1);
    }
    
}
