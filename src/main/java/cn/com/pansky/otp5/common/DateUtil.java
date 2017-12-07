package cn.com.pansky.otp5.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String FORMAT_1 = "yyyy-MM-dd";
    
    public static String FORMAT_2 = "yyyy-MM-dd HH:ss:mm";
    
    /**
     * 
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseStrToDate(String date) throws ParseException{
        SimpleDateFormat sd = new SimpleDateFormat(FORMAT_1);
        return sd.parse(date);
    }
    
    public static Date parseStrToDate(String date, String format) throws ParseException{
        SimpleDateFormat sd = new SimpleDateFormat(format);
        return sd.parse(date);
    }
    

    public static String parseDateToStr(Date date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_1);
        return sdf.format(date);
    }
    public static String parseDateToStr(Date date, String format) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    
    public static int getYearNew(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return  c.get(Calendar.YEAR);
    }

    public static int getYear(Date date){
        return date.getYear() + 1900;
    }
  

};
