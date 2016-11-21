package com.rmc.thienle.jedi2.implementation;

import android.provider.BaseColumns;

import com.rmc.thienle.jedi2.Util.StringHandler;
import com.rmc.thienle.jedi2.interfaces.Entry;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class EntryImpl implements Entry {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String COMMA_SEP = ",";

    /* Inner class that defines the table contents */
    public static class EntryDetails implements BaseColumns {
        public static final String TABLE_NAME = "entry";
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
    }
    public static final String SQL_CREATE_ENTRY_TBL =
            "CREATE TABLE " + EntryDetails.TABLE_NAME + " (" +
                    EntryDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EntryDetails.COLUMN_NAME_RELAY_PIN + INTEGER_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_ENTRY_NAME + TEXT_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_START_HR + INTEGER_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_START_MIN + INTEGER_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_START_SEC + INTEGER_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_END_HR + INTEGER_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_END_MIN + INTEGER_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_END_SEC + INTEGER_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_IS_WEEKDAY + TEXT_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_IS_MONTH + TEXT_TYPE + COMMA_SEP +
                    EntryDetails.COLUMN_NAME_SWITCH_ID + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY ("+EntryDetails.COLUMN_NAME_SWITCH_ID+") REFERENCES "+ SwitchImpl.SwitchDetails.TABLE_NAME+"("+SwitchImpl.SwitchDetails._ID+")"+
                    " FOREIGN KEY ("+EntryDetails.COLUMN_NAME_RELAY_PIN+") REFERENCES "+ RelayImpl.RelayDetails.TABLE_NAME+"("+RelayImpl.RelayDetails._ID+")"+
                    " )";

    public static final String SQL_DELETE_ENTRY_TBL = "DROP TABLE IF EXISTS " + EntryDetails.TABLE_NAME;

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

    public EntryImpl(String entryName, int entryId, int switchId, int relayPin,
                     int startHr, int startMin, int startSec,
                     int endHr, int endMin, int endSec,
                     String isWeekDay, String isMonth) {
        this.entryName = entryName;
        this.entryId = entryId;
        this.switchId = switchId;
        this.relayPin = relayPin;
        this.startHr = startHr;
        this.startMin = startMin;
        this.startSec = startSec;
        this.endHr = endHr;
        this.endMin = endMin;
        this.endSec = endSec;
        this.isWeekDay = isWeekDay;
        this.isMonth = isMonth;
    }

    @Override
    public int getEntryId() {
        return entryId;
    }

    @Override
    public String getEntryName() {
        return entryName;
    }

    @Override
    public String printOut(){
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
