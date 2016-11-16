package com.rmc.thienle.jedi2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rmc.thienle.jedi2.implementation.EntryImpl;
import com.rmc.thienle.jedi2.implementation.RelayImpl;
import com.rmc.thienle.jedi2.implementation.SwitchImpl;
import com.rmc.thienle.jedi2.interfaces.Entry;

/**
 * Created by thienle on 11/14/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    public static final String DATABASE_NAME = "JEDIDB.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRY_TBL =
            "CREATE TABLE " + EntryImpl.TABLE_NAME + " (" +
                    EntryImpl.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EntryImpl.COLUMN_NAME_RELAY_PIN + INTEGER_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_ENTRY_NAME + TEXT_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_START_HR + INTEGER_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_START_MIN + INTEGER_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_START_SEC + INTEGER_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_END_HR + INTEGER_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_END_MIN + INTEGER_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_END_SEC + INTEGER_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_IS_WEEKDAY + TEXT_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_IS_MONTH + TEXT_TYPE + COMMA_SEP +
                    EntryImpl.COLUMN_NAME_SWITCH_ID + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY ("+EntryImpl.COLUMN_NAME_SWITCH_ID+") REFERENCES "+ SwitchImpl.TABLE_NAME+"("+SwitchImpl.COLUMN_NAME_SWITCH_ID+")"+
                    " FOREIGN KEY ("+EntryImpl.COLUMN_NAME_RELAY_PIN+") REFERENCES "+ RelayImpl.TABLE_NAME+"("+RelayImpl.COLUMN_NAME_RELAY_PIN+")"+
                    " )";
    private static final String SQL_CREATE_SWITCH_TBL =
            "CREATE TABLE " + SwitchImpl.TABLE_NAME + " (" +
                    SwitchImpl.COLUMN_NAME_SWITCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SwitchImpl.COLUMN_NAME_SWITCH_NAME + TEXT_TYPE + COMMA_SEP +
                    SwitchImpl.COLUMN_NAME_SYNC_CODE + TEXT_TYPE + COMMA_SEP +
                    SwitchImpl.COLUMN_NAME_PASS_CODE + TEXT_TYPE + COMMA_SEP +
                    ")";
    private static final String SQL_CREATE_RELAY_TBL =
            "CREATE TABLE " + RelayImpl.TABLE_NAME + " (" +
                    RelayImpl.COLUMN_NAME_RELAY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RelayImpl.COLUMN_NAME_RELAY_NAME + TEXT_TYPE + COMMA_SEP +
                    RelayImpl.COLUMN_NAME_RELAY_PIN + INTEGER_TYPE + COMMA_SEP +
                    RelayImpl.COLUMN_NAME_RELAY_PRESET_TIME + FLOAT_TYPE + COMMA_SEP +
                    ")";

    private static final String SQL_DELETE_ENTRY_TBL = "DROP TABLE IF EXISTS " + EntryImpl.TABLE_NAME;
    private static final String SQL_DELETE_SWITCH_TBL = "DROP TABLE IF EXISTS " + SwitchImpl.TABLE_NAME;
    private static final String SQL_DELETE_RELAY_TBL = "DROP TABLE IF EXISTS " + RelayImpl.TABLE_NAME;

    protected SQLiteDatabase db = this.getWritableDatabase();

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SWITCH_TBL);
        db.execSQL(SQL_CREATE_RELAY_TBL);
        db.execSQL(SQL_CREATE_ENTRY_TBL);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(SQL_DELETE_ENTRY_TBL);
        db.execSQL(SQL_DELETE_SWITCH_TBL);
        db.execSQL(SQL_DELETE_RELAY_TBL);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
