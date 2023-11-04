package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {

    static SimpleDateFormat sdf = new SimpleDateFormat();

    /*Chuyển đổi String sang Date => return Date kết quả*/
    public static Date toDate(String date, String pattern) {
        try {
            sdf.applyPattern(pattern);
            return sdf.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*Chuyển đổi từ Date sang String => return String kết quả*/
    public static String toString(Date date, String pattern) {
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    /*Trả về thời gian hiện tại*/
    public static Date now() {
        return new Date();
    }

    /*Bổ sung số ngày vào thời gian*/
    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
}
