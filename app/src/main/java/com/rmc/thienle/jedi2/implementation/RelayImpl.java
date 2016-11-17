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
        return "Relay: "+relayName+" pin:"+relayPin+" preset time: "+presetTime;

    }

    @Override
    public int getRelayPin() {return relayPin; }

    @Override
    public String getRelayName() {return relayName; }

    @Override
    public float getPresetTime() {return presetTime; }
}
