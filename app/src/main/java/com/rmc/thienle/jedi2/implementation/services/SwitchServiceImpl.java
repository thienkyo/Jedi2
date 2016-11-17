package com.rmc.thienle.jedi2.implementation.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.rmc.thienle.jedi2.DataBaseHelper;
import com.rmc.thienle.jedi2.implementation.SwitchImpl;
import com.rmc.thienle.jedi2.interfaces.services.SwitchService;

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
        contentValues.put(SwitchImpl.COLUMN_NAME_SWITCH_NAME, switch_name);
        contentValues.put(SwitchImpl.COLUMN_NAME_SYNC_CODE, sync_code);
        contentValues.put(SwitchImpl.COLUMN_NAME_PASS_CODE, pass_code);
        return db.insert(SwitchImpl.TABLE_NAME, null, contentValues) > 0;
    }

    @Override
    public boolean updateSwitchById(int switch_id, String switch_name, String sync_code, String pass_code) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SwitchImpl.COLUMN_NAME_SWITCH_NAME, switch_name);
        contentValues.put(SwitchImpl.COLUMN_NAME_SYNC_CODE, sync_code);
        contentValues.put(SwitchImpl.COLUMN_NAME_PASS_CODE, pass_code);
        return db.update(SwitchImpl.TABLE_NAME, contentValues, SwitchImpl.COLUMN_NAME_SWITCH_ID + " = ? ", new String[]{String.valueOf(switch_id)}) > 0;
    }

    @Override
    public int deleteSwitch (int id) {
        //SQLiteDatabase db = this.getWritableDatabase();
        if(id != 0){
            return db.delete(SwitchImpl.TABLE_NAME, SwitchImpl.COLUMN_NAME_SWITCH_ID + " = ? ", new String[]{ String.valueOf(id) });
        }else {
            return db.delete(SwitchImpl.TABLE_NAME, null, null);
        }
    }

    /**
     * get one or all switch(id = 0) in database
     * @param id switchId
     * @return one or many switch
     */
    @Override
    public Cursor getSwitchById(int id) {
        return  db.rawQuery("select * from " + SwitchImpl.TABLE_NAME + " where " + SwitchImpl.COLUMN_NAME_SWITCH_ID + "=" + id, null);

    }
}
