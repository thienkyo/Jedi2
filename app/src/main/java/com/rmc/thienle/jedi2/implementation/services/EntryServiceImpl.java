package com.rmc.thienle.jedi2.implementation.services;

import android.content.ContentValues;
import android.content.Context;

import com.rmc.thienle.jedi2.DataBaseHelper;
import com.rmc.thienle.jedi2.implementation.EntryImpl;
import com.rmc.thienle.jedi2.interfaces.services.EntryService;

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
        contentValues.put(EntryImpl.COLUMN_NAME_ENTRY_NAME, entry_name);
        contentValues.put(EntryImpl.COLUMN_NAME_START_HR, start_hr);
        contentValues.put(EntryImpl.COLUMN_NAME_START_MIN, start_min);
        contentValues.put(EntryImpl.COLUMN_NAME_START_SEC, start_sec);
        contentValues.put(EntryImpl.COLUMN_NAME_END_HR, end_hr);
        contentValues.put(EntryImpl.COLUMN_NAME_END_MIN, end_min);
        contentValues.put(EntryImpl.COLUMN_NAME_END_SEC, end_sec);
        contentValues.put(EntryImpl.COLUMN_NAME_IS_WEEKDAY, is_weekday);
        contentValues.put(EntryImpl.COLUMN_NAME_IS_MONTH, is_month);
        contentValues.put(EntryImpl.COLUMN_NAME_RELAY_PIN, relay_pin);
        contentValues.put(EntryImpl.COLUMN_NAME_SWITCH_ID, switch_id);
        return db.insert(EntryImpl.TABLE_NAME, null, contentValues) > 0;
    }
}
