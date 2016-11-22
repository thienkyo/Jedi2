package com.rmc.thienle.jedi2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rmc.thienle.jedi2.implementation.EntryImpl;
import com.rmc.thienle.jedi2.implementation.RelayImpl;
import com.rmc.thienle.jedi2.implementation.SwitchImpl;

/**
 * Created by thienle on 11/14/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DataBaseHelper.class.getSimpleName();
    public static final String DATABASE_NAME = "JEDIDB.db";
    public static final int DATABASE_VERSION = 1;

    protected SQLiteDatabase db = this.getWritableDatabase();

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SwitchImpl.SQL_CREATE_SWITCH_TBL);
        db.execSQL(SwitchImpl.SQL_INITIAL_SWITCH_DATA);
        db.execSQL(RelayImpl.SQL_CREATE_RELAY_TBL);
        db.execSQL(RelayImpl.SQL_INITIAL_RELAY_DATA);
        db.execSQL(EntryImpl.SQL_CREATE_ENTRY_TBL);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(EntryImpl.SQL_DELETE_ENTRY_TBL);
        db.execSQL(SwitchImpl.SQL_DELETE_SWITCH_TBL);
        db.execSQL(RelayImpl.SQL_DELETE_RELAY_TBL);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
