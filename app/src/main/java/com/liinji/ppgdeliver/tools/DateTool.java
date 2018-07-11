package com.liinji.ppgdeliver.tools;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/17.
 */
public class DateTool {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
    public static final Calendar CALENDAR = Calendar.getInstance();
    public static String getDate(int index){

        switch (index){
            case 0:
                return getIntervalDateString(0);
            case 1:
                return getIntervalDateString(6);
            case 2:
                return getIntervalDateString(29);
        }

        return "";

    }

    @NonNull
    private static String getIntervalDateString(int duration) {
        SDF.applyPattern("yyyy/MM/dd");
        if (duration == 0){
            return SDF.format(new Date());
        }
        String end = SDF.format(new Date());
        CALENDAR.setTime(new Date());
        CALENDAR.add(Calendar.DAY_OF_MONTH,-duration);
        String start = SDF.format(CALENDAR.getTime());
        return start + "-" + end;
    }

    public static void setDateStrFormat(String format){
        SDF.applyPattern(format);
    }
    public static String getDateStr(Date date){
        return SDF.format(date);
    }

    public static String getDateStr(Date date, String format){
        if (date == null){
            return "";
        }
        SDF.applyPattern(format);
        return SDF.format(date);
    }

    public static String getDateStrAfterNow(int days){
        SDF.applyPattern("yyyy-MM-dd");
        if (days == 0){
            return SDF.format(new Date());
        }
        CALENDAR.setTime(new Date());
        CALENDAR.add(Calendar.DAY_OF_MONTH,days);

        return SDF.format(CALENDAR.getTime());
    }

    public static String getDateStrBeforeNow(int days){
        SDF.applyPattern("yyyy-MM-dd");
        if (days == 0){
            return SDF.format(new Date());
        }
        CALENDAR.setTime(new Date());
        CALENDAR.add(Calendar.DAY_OF_MONTH,-days);

        return SDF.format(CALENDAR.getTime());
    }

    public static String getDateStrAfterTheDate(Date date, int days){
        SDF.applyPattern("yyyy-MM-dd");
        if (days == 0){
            return SDF.format(date);
        }
        CALENDAR.setTime(date);
        CALENDAR.add(Calendar.DAY_OF_MONTH,days);

        return SDF.format(CALENDAR.getTime());
    }

    public static String getDateStrBeforeTheDate(Date date, int days){
        SDF.applyPattern("yyyy-MM-dd");
        if (days == 0){
            return SDF.format(date);
        }
        CALENDAR.setTime(date);
        CALENDAR.add(Calendar.DAY_OF_MONTH,-days);

        return SDF.format(CALENDAR.getTime());
    }

}
