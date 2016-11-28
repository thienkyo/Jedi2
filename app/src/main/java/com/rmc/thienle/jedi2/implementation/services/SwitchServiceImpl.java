package com.rmc.thienle.jedi2.implementation.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.rmc.thienle.jedi2.DataBaseHelper;
import com.rmc.thienle.jedi2.implementation.SwitchImpl;
import com.rmc.thienle.jedi2.interfaces.Switch;
import com.rmc.thienle.jedi2.interfaces.services.SwitchService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thien.lt on 11/16/2016.
 */

public class SwitchServiceImpl extends DataBaseHelper implements SwitchService {
    public SwitchServiceImpl(Context context) {
        super(context);
    }

    @Override
    public boolean insertSwitch(String switch_name, String sync_code, String pass_code) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SwitchImpl.SwitchDetails.COLUMN_NAME_SWITCH_NAME, switch_name);
        contentValues.put(SwitchImpl.SwitchDetails.COLUMN_NAME_SYNC_CODE, sync_code);
        contentValues.put(SwitchImpl.SwitchDetails.COLUMN_NAME_PASS_CODE, pass_code);
        return db.insert(SwitchImpl.SwitchDetails.TABLE_NAME, null, contentValues) > 0;
    }

    @Override
    public boolean updateSwitchById(int switch_id, String switch_name, String sync_code, String pass_code) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SwitchImpl.SwitchDetails.COLUMN_NAME_SWITCH_NAME, switch_name);
        contentValues.put(SwitchImpl.SwitchDetails.COLUMN_NAME_SYNC_CODE, sync_code);
        contentValues.put(SwitchImpl.SwitchDetails.COLUMN_NAME_PASS_CODE, pass_code);
        return db.update(SwitchImpl.SwitchDetails.TABLE_NAME, contentValues, SwitchImpl.SwitchDetails._ID + " = ? ", new String[]{String.valueOf(switch_id)}) > 0;
    }

    @Override
    public int deleteSwitchById(int id) {
        //SQLiteDatabase db = this.getWritableDatabase();
        if (id != 0) {
            return db.delete(SwitchImpl.SwitchDetails.TABLE_NAME, SwitchImpl.SwitchDetails._ID + " = ? AND " + SwitchImpl.SwitchDetails._ID + " !=1", new String[]{String.valueOf(id)});
        } else {
            return db.delete(SwitchImpl.SwitchDetails.TABLE_NAME, SwitchImpl.SwitchDetails._ID + " !=1", null);
        }
    }

    @Override
    public int deleteAllSwitch() {
        return deleteSwitchById(0);
    }

    /**
     * get one or all switch(id = 0) in database
     *
     * @param switchId switch_Id in Switch table
     * @return one switch
     */
    @Override
    public Switch getSwitchById(int switchId) {
        Cursor res = db.rawQuery("select * from " + SwitchImpl.SwitchDetails.TABLE_NAME + " where " + SwitchImpl.SwitchDetails._ID + "=" + switchId, null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            return new SwitchImpl(res.getInt(res.getColumnIndex(SwitchImpl.SwitchDetails._ID)),
                    res.getString(res.getColumnIndex(SwitchImpl.SwitchDetails.COLUMN_NAME_SWITCH_NAME)),
                    res.getString(res.getColumnIndex(SwitchImpl.SwitchDetails.COLUMN_NAME_SYNC_CODE)),
                    res.getString(res.getColumnIndex(SwitchImpl.SwitchDetails.COLUMN_NAME_PASS_CODE)));
        } else {
            return null;
        }
    }

    @Override
    public List<Switch> getAllSwitch() {
        Cursor res = db.rawQuery("select * from " + SwitchImpl.SwitchDetails.TABLE_NAME, null);
        if (res.getCount() > 0) {
            List<Switch> switchlist = new ArrayList<>();
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                switchlist.add(new SwitchImpl(res.getInt(res.getColumnIndex(SwitchImpl.SwitchDetails._ID)),
                        res.getString(res.getColumnIndex(SwitchImpl.SwitchDetails.COLUMN_NAME_SWITCH_NAME)),
                        res.getString(res.getColumnIndex(SwitchImpl.SwitchDetails.COLUMN_NAME_SYNC_CODE)),
                        res.getString(res.getColumnIndex(SwitchImpl.SwitchDetails.COLUMN_NAME_PASS_CODE))));
                res.moveToNext();
            }
            return switchlist;
        } else {
            return null;
        }
    }
}
