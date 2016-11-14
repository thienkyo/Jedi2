package com.rmc.thienle.jedi2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by thienle on 11/14/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    public static final String DATABASE_NAME = "JEDIDB.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRY_TBL =
            "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                    Entry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entry.COLUMN_NAME_RELAY_PIN + INTEGER_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_ENTRY_NAME + TEXT_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_START_HR + INTEGER_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_START_MIN + INTEGER_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_START_SEC + INTEGER_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_END_HR + INTEGER_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_END_MIN + INTEGER_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_END_SEC + INTEGER_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_IS_WEEKDAY + TEXT_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_IS_MONTH + TEXT_TYPE + COMMA_SEP +
                    Entry.COLUMN_NAME_SWITCH_ID + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY ("+Entry.COLUMN_NAME_SWITCH_ID+") REFERENCES "+Switch.TABLE_NAME+"("+Switch.COLUMN_NAME_SWITCH_ID+")"+
                    " FOREIGN KEY ("+Entry.COLUMN_NAME_RELAY_PIN+") REFERENCES "+Relay.TABLE_NAME+"("+Relay.COLUMN_NAME_RELAY_PIN+")"+
                    " )";
    private static final String SQL_CREATE_SWITCH_TBL =
            "CREATE TABLE " + Switch.TABLE_NAME + " (" +
                    Switch.COLUMN_NAME_SWITCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Switch.COLUMN_NAME_SWITCH_NAME + TEXT_TYPE + COMMA_SEP +
                    Switch.COLUMN_NAME_SYNC_CODE + TEXT_TYPE + COMMA_SEP +
                    Switch.COLUMN_NAME_PASS_CODE + TEXT_TYPE + COMMA_SEP +
                    ")";
    private static final String SQL_CREATE_RELAY_TBL =
            "CREATE TABLE " + Relay.TABLE_NAME + " (" +
                    Relay.COLUMN_NAME_RELAY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Relay.COLUMN_NAME_RELAY_NAME + TEXT_TYPE + COMMA_SEP +
                    Relay.COLUMN_NAME_RELAY_PIN + INTEGER_TYPE + COMMA_SEP +
                    Relay.COLUMN_NAME_RELAY_PRESET_TIME + FLOAT_TYPE + COMMA_SEP +
                    ")";

    private static final String SQL_DELETE_ENTRY_TBL = "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;
    private static final String SQL_DELETE_SWITCH_TBL = "DROP TABLE IF EXISTS " + Switch.TABLE_NAME;
    private static final String SQL_DELETE_RELAY_TBL = "DROP TABLE IF EXISTS " + Relay.TABLE_NAME;

    SQLiteDatabase db = this.getWritableDatabase();
    //private HashMap hp;

    public DBHelper(Context context) {
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

    public boolean insertEntry(String entry_name, int start_hr,int start_min, int start_sec,int end_hr,int end_min, int end_sec,
                                 String is_weekday, String is_month, int relay_pin, int switch_id)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Entry.COLUMN_NAME_ENTRY_NAME, entry_name);
        contentValues.put(Entry.COLUMN_NAME_START_HR, start_hr);
        contentValues.put(Entry.COLUMN_NAME_START_MIN, start_min);
        contentValues.put(Entry.COLUMN_NAME_START_SEC, start_sec);
        contentValues.put(Entry.COLUMN_NAME_END_HR, end_hr);
        contentValues.put(Entry.COLUMN_NAME_END_MIN, end_min);
        contentValues.put(Entry.COLUMN_NAME_END_SEC, end_sec);
        contentValues.put(Entry.COLUMN_NAME_IS_WEEKDAY, is_weekday);
        contentValues.put(Entry.COLUMN_NAME_IS_MONTH, is_month);
        contentValues.put(Entry.COLUMN_NAME_RELAY_PIN, relay_pin);
        contentValues.put(Entry.COLUMN_NAME_SWITCH_ID, switch_id);
        return db.insert(Entry.TABLE_NAME, null, contentValues) > 0;
    }

    public boolean insertSwitch(String switch_name, String sync_code, String pass_code){
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Switch.COLUMN_NAME_SWITCH_NAME, switch_name);
        contentValues.put(Switch.COLUMN_NAME_SYNC_CODE, sync_code);
        contentValues.put(Switch.COLUMN_NAME_PASS_CODE, pass_code);
        return db.insert(Switch.TABLE_NAME, null, contentValues) > 0;
    }

    public boolean insertRelay(String relay_name, int relay_pin, float preset_time){
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Relay.COLUMN_NAME_RELAY_NAME, relay_name);
        contentValues.put(Relay.COLUMN_NAME_RELAY_PIN, relay_pin);
        contentValues.put(Relay.COLUMN_NAME_RELAY_PRESET_TIME, preset_time);
        return db.insert(Relay.TABLE_NAME, null, contentValues) > 0;
    }

    public int deleteEntry (int id) {
        //SQLiteDatabase db = this.getWritableDatabase();
        if(id != 0){
            return db.delete(Entry.TABLE_NAME, Entry.COLUMN_NAME_ENTRY_ID + " = ? ", new String[]{ String.valueOf(id) });
        }else {
            return db.delete(Entry.TABLE_NAME, null, null);
        }
    }

    public int deleteSwitch (int id) {
        //SQLiteDatabase db = this.getWritableDatabase();
        if(id != 0){
            return db.delete(Switch.TABLE_NAME, Switch.COLUMN_NAME_SWITCH_ID + " = ? ", new String[]{ String.valueOf(id) });
        }else {
            return db.delete(Switch.TABLE_NAME, null, null);
        }
    }

    public int deleteRelay (int id) {
        //SQLiteDatabase db = this.getWritableDatabase();
        if(id != 0){
            return db.delete(Relay.TABLE_NAME, Relay.COLUMN_NAME_RELAY_ID + " = ? ", new String[]{ String.valueOf(id) });
        }else {
            return db.delete(Relay.TABLE_NAME, null, null);
        }
    }

    public Cursor getEntry(int id){
        //SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery("select * from " + Entry.TABLE_NAME + " where " + Entry.COLUMN_NAME_ENTRY_ID + "=" + id, null);
    }

    public Cursor getSwitch(int id){
        //SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery("select * from " + Switch.TABLE_NAME + " where " + Switch.COLUMN_NAME_SWITCH_ID + "=" + id, null);
    }

    public Cursor getRelay(int id){
        //SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery("select * from " + Relay.TABLE_NAME + " where " + Relay.COLUMN_NAME_RELAY_ID + "=" + id, null);
    }

    public boolean updateEntry (int entry_id,String entry_name, int start_hr,int start_min, int start_sec,int end_hr,int end_min, int end_sec,
                                String is_weekday, String is_month, int relay_pin, int switch_id)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Entry.COLUMN_NAME_ENTRY_NAME, entry_name);
        contentValues.put(Entry.COLUMN_NAME_START_HR, start_hr);
        contentValues.put(Entry.COLUMN_NAME_START_MIN, start_min);
        contentValues.put(Entry.COLUMN_NAME_START_SEC, start_sec);
        contentValues.put(Entry.COLUMN_NAME_END_HR, end_hr);
        contentValues.put(Entry.COLUMN_NAME_END_MIN, end_min);
        contentValues.put(Entry.COLUMN_NAME_END_SEC, end_sec);
        contentValues.put(Entry.COLUMN_NAME_IS_WEEKDAY, is_weekday);
        contentValues.put(Entry.COLUMN_NAME_IS_MONTH, is_month);
        contentValues.put(Entry.COLUMN_NAME_RELAY_PIN, relay_pin);
        contentValues.put(Entry.COLUMN_NAME_SWITCH_ID, switch_id);
        return db.update(Entry.TABLE_NAME, contentValues, Entry.COLUMN_NAME_ENTRY_ID + " = ? ", new String[] { String.valueOf(entry_id) }) > 0;
    }

    public boolean updateSwitch (int switch_id, String switch_name, String sync_code, String pass_code)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Switch.COLUMN_NAME_SWITCH_NAME, switch_name);
        contentValues.put(Switch.COLUMN_NAME_SYNC_CODE, sync_code);
        contentValues.put(Switch.COLUMN_NAME_PASS_CODE, pass_code);
        return db.update(Switch.TABLE_NAME, contentValues, Switch.COLUMN_NAME_SWITCH_ID + " = ? ", new String[] { String.valueOf(switch_id) }) > 0;
    }

    public boolean updateRelay (int relay_id, String relay_name, int relay_pin, float preset_time)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Relay.COLUMN_NAME_RELAY_NAME, relay_name);
        contentValues.put(Relay.COLUMN_NAME_RELAY_PIN, relay_pin);
        contentValues.put(Relay.COLUMN_NAME_RELAY_PRESET_TIME, preset_time);
        return db.update(Relay.TABLE_NAME, contentValues, Relay.COLUMN_NAME_RELAY_ID + " = ? ", new String[] { String.valueOf(relay_id) }) > 0;
    }

    public ArrayList<Entry> getAllEntryBySwitch()
    {
        ArrayList<Entry> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+Entry.TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Entry(res.getString(res.getColumnIndex(Entry.COLUMN_NAME_ENTRY_NAME)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_START_HR)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_START_MIN)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_END_HR)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_END_MIN)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_ENTRY_ID))
            ));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Entry> getFullEntries()
    {
        ArrayList<Entry> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+Entry.TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Entry(res.getString(res.getColumnIndex(Entry.COLUMN_NAME_ENTRY_NAME)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_ISACTIVE)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_START_HR)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_START_MIN)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_END_HR)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_END_MIN)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_MON)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_TUE)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_WEN)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_THU)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_FRI)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_SAT)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_SUN)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_DATE)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_RELAYID)),
                    res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_ENTRY_ID))
            ));
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<Integer> getAllEntry_id()
    {
        ArrayList<Integer> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+Entry.TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getInt(res.getColumnIndex(Entry.COLUMN_NAME_ENTRY_ID)));
            res.moveToNext();
        }
        return array_list;
    }

    /* Inner class that defines the table contents */
    public static abstract class Switch implements BaseColumns {
        public static final String TABLE_NAME = "switch";
        public static final String COLUMN_NAME_SWITCH_ID = "switch_id";
        public static final String COLUMN_NAME_SWITCH_NAME = "switch_name";
        public static final String COLUMN_NAME_SYNC_CODE = "sync_code";
        public static final String COLUMN_NAME_PASS_CODE = "pass_code";
    }
    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME_SWITCH_ID = "switch_id"; // a whole switch device
        public static final String COLUMN_NAME_RELAY_PIN = "relay_pin"; // on arduino
        public static final String COLUMN_NAME_ENTRY_NAME = "entry_name";
        public static final String COLUMN_NAME_START_HR = "start_hr";
        public static final String COLUMN_NAME_START_MIN = "start_min";
        public static final String COLUMN_NAME_START_SEC = "start_sec";
        public static final String COLUMN_NAME_END_HR = "end_hr";
        public static final String COLUMN_NAME_END_MIN = "end_min";
        public static final String COLUMN_NAME_END_SEC = "end_sec";
        public static final String COLUMN_NAME_IS_WEEKDAY = "is_weekday";
        public static final String COLUMN_NAME_IS_MONTH = "is_month";
    }

    public static abstract class Relay implements BaseColumns {
        public static final String TABLE_NAME = "relay";
        public static final String COLUMN_NAME_RELAY_ID = "relay_id";
        public static final String COLUMN_NAME_RELAY_PIN = "relay_pin";//on arduino
        public static final String COLUMN_NAME_RELAY_NAME = "relay_name";
        public static final String COLUMN_NAME_RELAY_PRESET_TIME = "relay_preset_time";
    }
}
