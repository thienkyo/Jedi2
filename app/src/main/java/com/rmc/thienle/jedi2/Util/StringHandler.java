package com.rmc.thienle.jedi2.Util;


import android.support.annotation.Nullable;

import com.rmc.thienle.jedi2.interfaces.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class StringHandler {
    private static String[] weekDayArray = {"Mon","Tue","Wen","Thu","Fri","Sat","Sun"};
    private static String[] monthArray = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    public static String[] monthArray2 = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    /**
     * convert string isWeekDay in database into readable string.
     * @param isWeekDay ex: "1,1,1,1,1,1,1"
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
     * @param isMonth ex: "1,1,1,1,1,1,1,1,1,1,1,1"
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
                str = str.substring(0,str.length()-1);
            }
        }
        return str;
    }

    public static List<String> convertEntryToTransmit(List<Entry> entryList){
        String data="";
        List<String> result = new ArrayList<>();
        int size = entryList.size();
        Entry en;
        for(int i =0;i< size; i++){
            en = entryList.get(i);
            data = en.toTransmitString();
            if(size==1){
                data = "A:"+data+":O";
            }else if(i==0 && size != 1){
                data = "A:"+data+":F";
            }else if(i==size-1){
                data = "A:"+data+":L";
            }else {
                data = "A:"+data+":M";
            }
            result.add(data);
        }
        return result;
    }

}
