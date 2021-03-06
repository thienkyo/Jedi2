package com.rmc.thienle.jedi2.implementation.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.rmc.thienle.jedi2.DataBaseHelper;
import com.rmc.thienle.jedi2.implementation.RelayImpl;
import com.rmc.thienle.jedi2.interfaces.Relay;
import com.rmc.thienle.jedi2.interfaces.services.RelayService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class RelayServiceImpl extends DataBaseHelper implements RelayService {
    public RelayServiceImpl(Context context) {
        super(context);
    }

    @Override
    public boolean insertRelay(String relay_name, int relay_pin,long switchId, float preset_time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_NAME, relay_name);
        contentValues.put(RelayImpl.RelayDetails._ID, relay_pin);
        contentValues.put(RelayImpl.RelayDetails.COLUMN_NAME_SWITCH_ID, switchId);
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
    public Relay getRelayBySwitchIdRelayPin(int switchId, int relayPin) {
        Cursor res =  db.rawQuery("select * from " + RelayImpl.RelayDetails.TABLE_NAME + " where " + RelayImpl.RelayDetails._ID + "=" + relayPin +" and "+ RelayImpl.RelayDetails.COLUMN_NAME_SWITCH_ID +"="+switchId, null);
        if(res.getCount()>0){
            res.moveToFirst();
            return new RelayImpl(res.getString(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_NAME)),
                    res.getInt(res.getColumnIndex(RelayImpl.RelayDetails._ID)),
                    res.getFloat(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME)),
                    res.getInt(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_SWITCH_ID))
                    );
        }else{
            return null;
        }
    }

    @Override
    public List<Relay> getAllRelay() {
        Cursor res =  db.rawQuery("select * from "+ RelayImpl.RelayDetails.TABLE_NAME, null );
        if(res.getCount()>0){
            List<Relay> relaylist = new ArrayList<>();
            res.moveToFirst();
            while(res.isAfterLast() == false){
                relaylist.add(new RelayImpl(res.getString(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_NAME)),
                        res.getInt(res.getColumnIndex(RelayImpl.RelayDetails._ID)),
                        res.getFloat(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME)),
                        res.getInt(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_SWITCH_ID)))
                );
                res.moveToNext();
            }
            return relaylist;
        }else{
            return null;
        }
    }

    @Override
    public List<Relay> getRelayBySwitchId(int switchId) {
        Cursor res =  db.rawQuery("select * from "+ RelayImpl.RelayDetails.TABLE_NAME + " where " + RelayImpl.RelayDetails.COLUMN_NAME_SWITCH_ID + "=" + switchId, null );
        if(res.getCount()>0){
            List<Relay> relaylist = new ArrayList<>();
            res.moveToFirst();
            while(res.isAfterLast() == false){
                relaylist.add(new RelayImpl(res.getString(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_NAME)),
                        res.getInt(res.getColumnIndex(RelayImpl.RelayDetails._ID)),
                        res.getFloat(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_RELAY_PRESET_TIME)),
                        res.getInt(res.getColumnIndex(RelayImpl.RelayDetails.COLUMN_NAME_SWITCH_ID)))
                );
                res.moveToNext();
            }
            return relaylist;
        }else{
            return null;
        }
    }
}
