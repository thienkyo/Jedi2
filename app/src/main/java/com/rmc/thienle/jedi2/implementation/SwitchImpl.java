package com.rmc.thienle.jedi2.implementation;

import android.provider.BaseColumns;

import com.rmc.thienle.jedi2.interfaces.Switch;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class SwitchImpl implements Switch {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    /* Inner class that defines the table contents */
    public static class SwitchDetails implements BaseColumns {
        public static final String TABLE_NAME = "switch";
        //    public static final String COLUMN_NAME_SWITCH_ID = "switch_id";
        public static final String COLUMN_NAME_SWITCH_NAME = "switch_name";
        public static final String COLUMN_NAME_SYNC_CODE = "sync_code";
        public static final String COLUMN_NAME_PASS_CODE = "pass_code";
    }

    public static final String SQL_CREATE_SWITCH_TBL = "CREATE TABLE " + SwitchDetails.TABLE_NAME + " (" +
            SwitchDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SwitchDetails.COLUMN_NAME_SWITCH_NAME + TEXT_TYPE + COMMA_SEP +
            SwitchDetails.COLUMN_NAME_SYNC_CODE + TEXT_TYPE + COMMA_SEP +
            SwitchDetails.COLUMN_NAME_PASS_CODE + TEXT_TYPE  + ")";
    public static final String SQL_DELETE_SWITCH_TBL = "DROP TABLE IF EXISTS " + SwitchDetails.TABLE_NAME;
    public static final String SQL_INITIAL_SWITCH_DATA = "INSERT INTO " + SwitchDetails.TABLE_NAME +
            "(" +
            SwitchDetails.COLUMN_NAME_SWITCH_NAME + COMMA_SEP +
            SwitchDetails.COLUMN_NAME_SYNC_CODE + COMMA_SEP +
            SwitchDetails.COLUMN_NAME_PASS_CODE +
            ") values('Default switch','0,0','0,0')";

    private int switchId;
    private String switchName;
    private String syncCode;
    private String passCode;

    public SwitchImpl(int switchId, String switchName, String syncCode, String passCode) {
        this.switchId = switchId;
        this.switchName = switchName;
        this.syncCode = syncCode;
        this.passCode = passCode;
    }

    @Override
    public String printOut() {
        return "Name: " + switchName + " id:" + switchId + " sync code:" + syncCode + " pass code:" + passCode;
    }

    @Override
    public int getSwitchId() {
        return switchId;
    }

    @Override
    public String getSwitchName() {
        return switchName;
    }

    @Override
    public String getSyncCode() {
        return syncCode;
    }

    @Override
    public String getPassCode() {
        return passCode;
    }
}
