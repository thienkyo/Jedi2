package com.rmc.thienle.jedi2.implementation;

import android.provider.BaseColumns;

import com.rmc.thienle.jedi2.interfaces.Relay;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class RelayImpl implements Relay {
    private static final String TEXT_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    /* Inner class that defines the table contents */
    public static class RelayDetails implements BaseColumns {
        public static final String TABLE_NAME = "relay";
        //public static final String COLUMN_NAME_RELAY_PIN = "relay_pin";//on arduino
        public static final String COLUMN_NAME_RELAY_NAME = "relay_name";
        public static final String COLUMN_NAME_RELAY_PRESET_TIME = "relay_preset_time";
        public static final String COLUMN_NAME_SWITCH_ID = "switch_id";
    }

    public static final String SQL_CREATE_RELAY_TBL =
            "CREATE TABLE " + RelayDetails.TABLE_NAME + " (" +
                    RelayDetails._ID + " INTEGER ," +
                    RelayDetails.COLUMN_NAME_RELAY_NAME + TEXT_TYPE + COMMA_SEP +
                    RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME + FLOAT_TYPE + COMMA_SEP +
                    RelayDetails.COLUMN_NAME_SWITCH_ID + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + RelayDetails.COLUMN_NAME_SWITCH_ID + ") REFERENCES " + SwitchImpl.SwitchDetails.TABLE_NAME + "(" + SwitchImpl.SwitchDetails._ID + ") ON DELETE CASCADE" +
                    ")";
    public static final String SQL_DELETE_RELAY_TBL = "DROP TABLE IF EXISTS " + RelayDetails.TABLE_NAME;
    public static final String SQL_INITIAL_RELAY_DATA_1 = "INSERT INTO " + RelayDetails.TABLE_NAME +
            "(" +
            RelayDetails._ID + COMMA_SEP +
            RelayDetails.COLUMN_NAME_RELAY_NAME + COMMA_SEP +
            RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME + COMMA_SEP +
            RelayDetails.COLUMN_NAME_SWITCH_ID +
            ") values(6,'Plumber',0,1) ;";

    public static final String SQL_INITIAL_RELAY_DATA_2 = "INSERT INTO " + RelayDetails.TABLE_NAME +
            "(" + RelayDetails._ID + COMMA_SEP +
            RelayDetails.COLUMN_NAME_RELAY_NAME + COMMA_SEP +
            RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME + COMMA_SEP +
            RelayDetails.COLUMN_NAME_SWITCH_ID +
            ") values(7,'Lights',0.5,1);";
    public static final String SQL_INITIAL_RELAY_DATA_3 = "INSERT INTO " + RelayDetails.TABLE_NAME +
            "(" +
            RelayDetails._ID + COMMA_SEP +
            RelayDetails.COLUMN_NAME_RELAY_NAME + COMMA_SEP +
            RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME + COMMA_SEP +
            RelayDetails.COLUMN_NAME_SWITCH_ID +
            ") values(6,'outlet 1 vegetable garden',0,2) ;";

    public static final String SQL_INITIAL_RELAY_DATA_4 = "INSERT INTO " + RelayDetails.TABLE_NAME +
            "(" + RelayDetails._ID + COMMA_SEP +
            RelayDetails.COLUMN_NAME_RELAY_NAME + COMMA_SEP +
            RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME + COMMA_SEP +
            RelayDetails.COLUMN_NAME_SWITCH_ID +
            ") values(7,'outlet 2 vegetable garden',0.5,2);";

    private String relayName;
    private int relayPin;
    private float presetTime;
    private int switchId;

    public RelayImpl(String relayName, int relayPin, float presetTime, int switchId) {
        this.relayName = relayName;
        this.relayPin = relayPin;
        this.presetTime = presetTime;
        this.switchId = switchId;
    }

    @Override
    public String printOut() {
        return "Relay:" + relayName + " pin:" + relayPin + " preset time:" + presetTime + " switchid:"+switchId;

    }

    @Override
    public int getRelayPin() {
        return relayPin;
    }

    @Override
    public String getRelayName() {
        return relayName;
    }

    @Override
    public float getPresetTime() {
        return presetTime;
    }
}
