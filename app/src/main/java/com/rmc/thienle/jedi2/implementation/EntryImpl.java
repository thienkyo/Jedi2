package com.rmc.thienle.jedi2.implementation;

import com.rmc.thienle.jedi2.Util.StringHandler;
import com.rmc.thienle.jedi2.interfaces.Entry;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class EntryImpl implements Entry {
    public static final String TABLE_NAME = "entry";
    public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
    public static final String COLUMN_NAME_SWITCH_ID = "switch_id"; // a whole switch device
    public static final String COLUMN_NAME_RELAY_PIN = "relay_pin"; // on arduino
    public static final String COLUMN_NAME_ENTRY_NAME = "entry_name";
    public static final String COLUMN_NAME_START_HR = "start_hr";
    public static final String COLUMN_NAME_START_MIN = "start_min";
    public static final String COLUMN_NAME_START_SEC = "start_sec";
    public static final String COLUMN_NAME_END_HR = "end_hr";
    public static final String COLUMN_NAME_END_MIN = "end_min";
    public static final String COLUMN_NAME_END_SEC = "end_sec";
    public static final String COLUMN_NAME_IS_WEEKDAY = "is_weekday";
    public static final String COLUMN_NAME_IS_MONTH = "is_month";

    private String entryName;
    private int entryId;
    private int switchId;
    private int relayPin;
    private int startHr;
    private int startMin;
    private int startSec;
    private int endHr;
    private int endMin;
    private int endSec;
    private String isWeekDay;
    private String isMonth;

    @Override
    public int getEntryId() {
        return entryId;
    }

    @Override
    public String getEntryName() {
        return entryName;
    }

    @Override
    public String toString(){
        String weekDay = StringHandler.isWeekDayConversion(isWeekDay);
        String month = StringHandler.isMonthConversion(isMonth);

        return entryName +" - "+month+" "+weekDay+
                (startHr >9 ? startHr+"" : "0"+startHr) +":"+ (startMin > 9 ? startMin+"" : "0"+startMin)+ " -> "+":"+(startSec > 9 ? startSec+"" : "0"+startSec)+
                (endHr >9 ? endHr+"" : "0"+endHr) +":"+ (endMin > 9 ? endMin+"" : "0"+endMin)+":"+(endSec > 9 ? endSec+"" : "0"+endSec)
                ;
    }

    @Override
    public String toRaw(){  //6,18,0,0,18,30,0, 1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1
        return entryName+
                ", entryId="+entryId+
                ", switchId="+switchId+
                ", relayPin="+relayPin+
                ", start:"+startHr+":"+startMin+":"+startSec+
                ", end: "+endHr+","+endMin+","+endSec+
                ", "+StringHandler.isWeekDayConversion(isWeekDay)+
                ", "+StringHandler.isMonthConversion(isMonth);
    }
}
