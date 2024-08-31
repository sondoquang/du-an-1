
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class XDate {

//    private static SimpleDateFormat formater = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(Date date, String pattern) {
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        return formater.format(date);
    }
    
    public static Date addDays(Date date, long days){
        date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }
    
    public static boolean validate(String date, String pattern){
        try {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            formater.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public static Boolean check(String DateBefore, String DateAfter, String pattern){
        Date ngayTruoc = XDate.toDate(DateBefore, pattern);
        Date ngaySau = XDate.toDate(DateAfter, pattern);
        long TDate = ngayTruoc.getTime();
        long SDate = ngaySau.getTime();
        long HDate = SDate - TDate;
        long result = HDate/(24*60*60*1000);
        if(result <= 0){
            return false;
        }
        return true;
    }
}
