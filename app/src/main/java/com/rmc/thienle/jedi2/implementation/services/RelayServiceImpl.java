package com.rmc.thienle.jedi2.implementation.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.rmc.thienle.jedi2.DataBaseHelper;
import com.rmc.thienle.jedi2.implementation.RelayImpl;
import com.rmc.thienle.jedi2.interfaces.Relay;
import com.rmc.thienle.jedi2.interfaces.services.RelayService;

import java.util.ArrayList;

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
        contentValues.put(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_NAME, relay_name);
        contentValues.put(RelayImpl.RelayDetails._ID, relay_pin);
        contentValues.put(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME, preset_time);
        return db.insert(RelayImpl.RelayDetails.TABLE_NAME, null, contentValues) > 0;
    }

    @Override
    public boolean updateRelay(String relay_name, int relay_pin, float preset_time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_NAME, relay_name);
        contentValues.put(RelayImpl.RelayDetails._ID, relay_pin);
        contentValues.put(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME, preset_time);
        return db.update(RelayImpl.RelayDetails.TABLE_NAME, contentValues, RelayImpl.RelayDetails._ID + " = ? ", new String[] { String.valueOf(relay_pin) }) > 0;
    }

    @Override
    public int deleteRelay(int id) {
        if(id != 0){
            return db.delete(RelayImpl.RelayDetails.TABLE_NAME, RelayImpl.RelayDetails._ID + " = ? ", new String[]{ String.valueOf(id) });
        }else {
            return db.delete(RelayImpl.RelayDetails.TABLE_NAME, null, null);
        }
    }

    @Override
    public int deleteAllRelay() {
        return deleteRelay(0);
    }

    @Override
    public Relay getRelayById(int relayPin) {
        Cursor res =  db.rawQuery("select * from " + RelayImpl.RelayDetails.TABLE_NAME + " where " + RelayImpl.RelayDetails._ID + "=" + relayPin, null);
        if(res.getCount()>0){
            res.moveToFirst();
            return new RelayImpl(res.getString(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_NAME)),
                    res.getInt(res.getColumnIndex(RelayImpl.RelayDetails._ID)),
                    res.getFloat(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME)));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Relay> getAllRelay() {
        Cursor res =  db.rawQuery("select * from "+ RelayImpl.RelayDetails.TABLE_NAME, null );
        if(res.getCount()>0){
            ArrayList<Relay> array_list = new ArrayList<>();
            res.moveToFirst();
            while(res.isAfterLast() == false){
                array_list.add(new RelayImpl(res.getString(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_NAME)),
                        res.getInt(res.getColumnIndex(RelayImpl.RelayDetails._ID)),
                        res.getFloat(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME))));
                res.moveToNext();
            }
            return array_list;
        }else{
            return null;
        }
    }
}
