package com.rmc.thienle.jedi2.implementation.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.rmc.thienle.jedi2.DataBaseHelper;
import com.rmc.thienle.jedi2.implementation.EntryImpl;
import com.rmc.thienle.jedi2.interfaces.Entry;
import com.rmc.thienle.jedi2.interfaces.services.EntryService;

import java.util.ArrayList;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class EntryServiceImpl extends DataBaseHelper implements EntryService {
    public EntryServiceImpl(Context context) {
        super(context);
    }

    @Override
    public boolean insertEntry(String entry_name, int start_hr, int start_min, int start_sec,
                               int end_hr, int end_min, int end_sec, String is_weekday, String is_month, int relay_pin, int switch_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_ENTRY_NAME, entry_name);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_START_HR, start_hr);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_START_MIN, start_min);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_START_SEC, start_sec);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_END_HR, end_hr);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_END_MIN, end_min);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_END_SEC, end_sec);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_IS_WEEKDAY, is_weekday);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_IS_MONTH, is_month);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN, relay_pin);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID, switch_id);
        return db.insert(EntryImpl.EntryDetails.TABLE_NAME, null, contentValues) > 0;
    }

    @Override
    public boolean updateEntryById(int entry_id, String entry_name, int start_hr, int start_min, int start_sec, int end_hr, int end_min, int end_sec, String is_weekday, String is_month, int relay_pin, int switch_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_ENTRY_NAME, entry_name);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_START_HR, start_hr);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_START_MIN, start_min);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_START_SEC, start_sec);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_END_HR, end_hr);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_END_MIN, end_min);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_END_SEC, end_sec);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_IS_WEEKDAY, is_weekday);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_IS_MONTH, is_month);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN, relay_pin);
        contentValues.put(EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID, switch_id);
        return db.update(EntryImpl.EntryDetails.TABLE_NAME, contentValues, EntryImpl.EntryDetails._ID + " = ? ", new String[] { String.valueOf(entry_id) }) > 0;
    }

    @Override
    public ArrayList<Entry> getAllEntryBySwitchId(int switchId) {
        Cursor res =  db.rawQuery( "select * from "+EntryImpl.EntryDetails.TABLE_NAME+ " where " + EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID + "=" + switchId, null );
        if(res.getCount()>0){
            ArrayList<Entry> array_list = new ArrayList<>();
            res.moveToFirst();
            while(res.isAfterLast() == false){
                array_list.add(new EntryImpl(res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_ENTRY_NAME)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails._ID)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_HR)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_MIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_SEC)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_HR)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_MIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_SEC)),
                        res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_WEEKDAY)),
                        res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_MONTH))
                ));
                res.moveToNext();
            }
            return array_list;
        }else{
            return null;
        }

    }

    @Override
    public ArrayList<Entry> getAllEntryByRelayPin(int relayPin) {
        ArrayList<Entry> array_list = new ArrayList<>();
        Cursor res =  db.rawQuery( "select * from "+EntryImpl.EntryDetails.TABLE_NAME+ " where " + EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN + "=" + relayPin, null );
        if(res.getCount()>0){
            res.moveToFirst();
            while(res.isAfterLast() == false){
                array_list.add(new EntryImpl(res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_ENTRY_NAME)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails._ID)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_HR)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_MIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_SEC)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_HR)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_MIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_SEC)),
                        res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_WEEKDAY)),
                        res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_MONTH))
                ));
                res.moveToNext();
            }
            return array_list;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Entry> getAllEntryBySwitchIdRelayPin(int switchId, int relayPin) {
        ArrayList<Entry> array_list = new ArrayList<>();
        Cursor res =  db.rawQuery( "select * from "+EntryImpl.EntryDetails.TABLE_NAME+ " where " +
                EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN + "=" + relayPin + " and "+
                EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID + "=" + switchId, null );
        if(res.getCount()>0){
            res.moveToFirst();
            while(res.isAfterLast() == false){
                array_list.add(new EntryImpl(res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_ENTRY_NAME)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails._ID)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_HR)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_MIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_SEC)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_HR)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_MIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_SEC)),
                        res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_WEEKDAY)),
                        res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_MONTH))
                ));
                res.moveToNext();
            }
            return array_list;
        }else{
            return null;
        }
    }

    @Override
    public Entry getEntryById(int id) {
        Cursor res =  db.rawQuery("select * from " + EntryImpl.EntryDetails.TABLE_NAME + " where " + EntryImpl.EntryDetails._ID + "=" + id, null);
        if(res.getCount()>0){
            res.moveToFirst();
            return new EntryImpl(res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_ENTRY_NAME)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails._ID)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_HR)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_MIN)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_SEC)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_HR)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_MIN)),
                    res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_SEC)),
                    res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_WEEKDAY)),
                    res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_MONTH))
            );
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Entry> getAllEntry() {
        Cursor res =  db.rawQuery("select * from "+EntryImpl.EntryDetails.TABLE_NAME, null );
        if(res.getCount()>0){
            ArrayList<Entry> array_list = new ArrayList<>();
            res.moveToFirst();
            while(res.isAfterLast() == false){
                array_list.add(new EntryImpl(res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_ENTRY_NAME)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails._ID)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_HR)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_MIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_START_SEC)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_HR)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_MIN)),
                        res.getInt(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_END_SEC)),
                        res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_WEEKDAY)),
                        res.getString(res.getColumnIndex(EntryImpl.EntryDetails.COLUMN_NAME_IS_MONTH))
                ));
                res.moveToNext();
            }
            return array_list;
        }else{
            return null;
        }
    }

    /**
     * delete one or all(id = 0) entries in database
     * @param id entry_id col on entry table
     * @return int. number of affected record.
     */
    @Override
    public int deleteEntryById(int id) {
        if(id != 0){
            return db.delete(EntryImpl.EntryDetails.TABLE_NAME, EntryImpl.EntryDetails._ID + " = ? ", new String[]{ String.valueOf(id) });
        }else {
            return db.delete(EntryImpl.EntryDetails.TABLE_NAME, null, null);
        }
    }

    @Override
    public int deleteAllEntry(){
        return deleteEntryById(0);
    }

    @Override
    public int deleteEntryBySwitchId(int switchId) {
        return db.delete(EntryImpl.EntryDetails.TABLE_NAME, EntryImpl.EntryDetails.COLUMN_NAME_SWITCH_ID + " = ? ", new String[]{ String.valueOf(switchId) });
    }

    @Override
    public int deleteEntryByRelayPin(int relayPin){
        return db.delete(EntryImpl.EntryDetails.TABLE_NAME, EntryImpl.EntryDetails.COLUMN_NAME_RELAY_PIN + " = ? ", new String[]{ String.valueOf(relayPin) });
    }
}
