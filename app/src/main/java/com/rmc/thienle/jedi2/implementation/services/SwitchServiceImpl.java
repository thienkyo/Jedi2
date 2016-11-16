package com.rmc.thienle.jedi2.implementation.services;

import android.content.ContentValues;
import android.content.Context;

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
}
