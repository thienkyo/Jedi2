package com.rmc.thienle.jedi2.implementation;

import com.rmc.thienle.jedi2.interfaces.Relay;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class RelayImpl implements Relay {
    public static final String TABLE_NAME = "relay";
    public static final String COLUMN_NAME_RELAY_PIN = "relay_pin";//on arduino
    public static final String COLUMN_NAME_RELAY_NAME = "relay_name";
    public static final String COLUMN_NAME_RELAY_PRESET_TIME = "relay_preset_time";

    private String relayName;
    private int relayPin;
    private float presetTime;

    @Override
    public String printOut() {
        return null;
    }

    @Override
    public int getRelayPin() {
        return 0;
    }

    @Override
    public String getRelayName() {
        return null;
    }
}
