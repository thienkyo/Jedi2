package com.rmc.thienle.jedi2.implementation.services;

import android.content.ContentValues;
import android.content.Context;

import com.rmc.thienle.jedi2.DataBaseHelper;
import com.rmc.thienle.jedi2.implementation.RelayImpl;
import com.rmc.thienle.jedi2.interfaces.services.RelayService;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class RelayServiceImpl extends DataBaseHelper implements RelayService {
    public RelayServiceImpl(Context context) {
        super(context);
    }

    @Override
    public boolean insertRelay(String relay_name, int relay_pin, float preset_time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RelayImpl.COLUMN_NAME_RELAY_NAME, relay_name);
        contentValues.put(RelayImpl.COLUMN_NAME_RELAY_PIN, relay_pin);
        contentValues.put(RelayImpl.COLUMN_NAME_RELAY_PRESET_TIME, preset_time);
        return db.insert(RelayImpl.TABLE_NAME, null, contentValues) > 0;
    }

    @Override
    public int deleteRelay(int id) {
        if(id != 0){
            return db.delete(RelayImpl.TABLE_NAME, RelayImpl.COLUMN_NAME_RELAY_ID + " = ? ", new String[]{ String.valueOf(id) });
        }else {
            return db.delete(RelayImpl.TABLE_NAME, null, null);
        }
    }
}
