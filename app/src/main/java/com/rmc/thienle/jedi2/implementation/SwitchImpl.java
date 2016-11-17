package com.rmc.thienle.jedi2.implementation;

import com.rmc.thienle.jedi2.interfaces.Switch;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class SwitchImpl implements Switch {
    public static final String TABLE_NAME = "switch";
    public static final String COLUMN_NAME_SWITCH_ID = "switch_id";
    public static final String COLUMN_NAME_SWITCH_NAME = "switch_name";
    public static final String COLUMN_NAME_SYNC_CODE = "sync_code";
    public static final String COLUMN_NAME_PASS_CODE = "pass_code";

    private int switchId;
    private String switchName;
    private String syncCode;
    private String passCode;


    @Override
    public String printOut() {
        return "Name: "+switchName+" id:"+switchId+" sync code:"+syncCode+" pass code:"+passCode;
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
