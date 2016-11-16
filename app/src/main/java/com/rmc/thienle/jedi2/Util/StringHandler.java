package com.rmc.thienle.jedi2.Util;

import com.rmc.thienle.jedi2.implementation.services.EntryServiceImpl;
import com.rmc.thienle.jedi2.interfaces.services.EntryService;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class StringHandler {
    public static String[] weekDayArray = {"Mon","Tue","Wen","Thu","Fri","Sat","Sun"};
    public static String[] monthArray = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    public static String[] monthArray2 = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    /**
     * convert string isWeekDay in database into readable string.
     * @param isWeekDay
     * @return String. readable string
     */
    public static String isWeekDayConversion(String isWeekDay){
        String weekday = "Week: ";
        boolean flag = true;
        String[] isWeekDayArray = isWeekDay.split(",");
        for(int i = 0; i < 7; i++){
            if(isWeekDayArray[i].equals("1")){
                weekday += weekDayArray[i]+",";
            }else{
                flag = false;
            }
        }
        if(flag){
            weekday = "Week: Everyday";
        }
        return trimLastSeparator(",",weekday);
    }

    /**
     * convert string isMonth in database into readable string.
     * @param isMonth
     * @return String. readable string
     */
    public static String isMonthConversion(String isMonth){
        String month = "Month: ";
        boolean flag = true;
        String[] isMonthArray = isMonth.split(",");
        for(int i = 0; i < 12; i++){
            if(isMonthArray[i].equals("1")){
                month += monthArray[i]+",";
            }else{
                flag = false;
            }
        }
        if(flag){
            month = "Month: Every month";
        }
        return trimLastSeparator(",",month);
    }

    public static String trimLastSeparator(String separator, String str){
        if(str.length() > 0){
            if(str.lastIndexOf(separator) == str.length() - 1){
                str = str.substring(0,str.length()-2);
            }
        }
        return str;
    }
}
