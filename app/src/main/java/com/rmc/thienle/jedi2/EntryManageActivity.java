package com.rmc.thienle.jedi2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.ikovac.timepickerwithseconds.MyTimePickerDialog;
import com.rmc.thienle.jedi2.Util.StringHandler;
import com.rmc.thienle.jedi2.implementation.services.EntryServiceImpl;
import com.rmc.thienle.jedi2.interfaces.Entry;
import com.rmc.thienle.jedi2.interfaces.services.EntryService;

import java.util.Calendar;
import java.util.List;

public class EntryManageActivity extends AppCompatActivity {
    private static final String TAG = EntryManageActivity.class.getSimpleName();
    private static EntryService entryService;
    private ToggleButton monBtn, tueBtn, wenBtn, thuBtn, friBtn, satBtn, sunBtn;
    private ToggleButton janBtn, febBtn, marBtn, aprBtn, mayBtn, junBtn, julBtn, augBtn, sepBtn, octBtn, novBtn, decBtn;
    private TextView fromTV, toTV;
    private EditText entryNameEV;
    private int entryId, relayPin, switchId;
    private List<ToggleButton> weekDayBtnList, monthBtnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        entryService = new EntryServiceImpl(this);

        monBtn = (ToggleButton) findViewById(R.id.mon);
        tueBtn = (ToggleButton) findViewById(R.id.tue);
        wenBtn = (ToggleButton) findViewById(R.id.wen);
        thuBtn = (ToggleButton) findViewById(R.id.thu);
        friBtn = (ToggleButton) findViewById(R.id.fri);
        satBtn = (ToggleButton) findViewById(R.id.sat);
        sunBtn = (ToggleButton) findViewById(R.id.sun);
        weekDayBtnList.add(monBtn);
        weekDayBtnList.add(tueBtn);
        weekDayBtnList.add(wenBtn);
        weekDayBtnList.add(thuBtn);
        weekDayBtnList.add(friBtn);
        weekDayBtnList.add(satBtn);
        weekDayBtnList.add(sunBtn);

        janBtn = (ToggleButton) findViewById(R.id.jan);
        febBtn = (ToggleButton) findViewById(R.id.feb);
        marBtn = (ToggleButton) findViewById(R.id.mar);
        aprBtn = (ToggleButton) findViewById(R.id.apr);
        mayBtn = (ToggleButton) findViewById(R.id.may);
        junBtn = (ToggleButton) findViewById(R.id.jun);
        julBtn = (ToggleButton) findViewById(R.id.jul);
        augBtn = (ToggleButton) findViewById(R.id.aug);
        sepBtn = (ToggleButton) findViewById(R.id.sep);
        octBtn = (ToggleButton) findViewById(R.id.oct);
        novBtn = (ToggleButton) findViewById(R.id.nov);
        decBtn = (ToggleButton) findViewById(R.id.dec);
        monthBtnList.add(janBtn);
        monthBtnList.add(febBtn);
        monthBtnList.add(marBtn);
        monthBtnList.add(aprBtn);
        monthBtnList.add(mayBtn);
        monthBtnList.add(junBtn);
        monthBtnList.add(julBtn);
        monthBtnList.add(augBtn);
        monthBtnList.add(sepBtn);
        monthBtnList.add(octBtn);
        monthBtnList.add(novBtn);
        monthBtnList.add(decBtn);

        fromTV = (TextView) findViewById(R.id.from);
        toTV = (TextView) findViewById(R.id.to);
        entryNameEV = (EditText) findViewById(R.id.entryManage_entryName);

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("EntryPackage");
        entryId = packageFromCaller.getInt("entryId", 0);
        relayPin = packageFromCaller.getInt("relayPin", 0);
        switchId = packageFromCaller.getInt("switchId", 0);
        Log.d(TAG, "entry id sent from mainAct: " + entryId);
        if (entryId != 0) {
            loadInfo(entryId);
        }

        fromTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                MyTimePickerDialog mTimePicker = new MyTimePickerDialog(EntryManageActivity.this, new MyTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker timePicker, int selectedHour, int selectedMinute, int selectedSecond) {
                        fromTV.setText((selectedHour > 9 ? selectedHour : "0" + selectedHour) + ":" + (selectedMinute > 9 ? selectedMinute : "0" + selectedMinute) + ":" + (selectedSecond > 9 ? selectedSecond : "0" + selectedSecond));
                    }
                }, mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), mcurrentTime.get(Calendar.SECOND), true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        toTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                MyTimePickerDialog mTimePicker = new MyTimePickerDialog(EntryManageActivity.this, new MyTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker timePicker, int selectedHour, int selectedMinute, int selectedSecond) {
                        fromTV.setText((selectedHour > 9 ? selectedHour : "0" + selectedHour) + ":" + (selectedMinute > 9 ? selectedMinute : "0" + selectedMinute) + ":" + (selectedSecond > 9 ? selectedSecond : "0" + selectedSecond));
                    }
                }, mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), mcurrentTime.get(Calendar.SECOND), true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_manage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.save_entry_manage) {

            if (manageEntry(entryId)) {
                onBackPressed();
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean manageEntry(int entryId) {
        Log.e(TAG, " update: entryId= " + entryId + " isMon: " + monBtn.isChecked());
        String entry_name = entryNameEV.getText().toString();
        String isWeekDay = "";
        for (ToggleButton btn : weekDayBtnList) {
            if (btn.isChecked()) {
                isWeekDay += "1,";
            } else {
                isWeekDay += "0,";
            }
        }
        isWeekDay = StringHandler.trimLastSeparator(",", isWeekDay);
        String isMonth = "";
        for (ToggleButton btn : monthBtnList) {
            if (btn.isChecked()) {
                isMonth += "1,";
            } else {
                isMonth += "0,";
            }
        }
        isMonth = StringHandler.trimLastSeparator(",", isMonth);

        String[] from = fromTV.getText().toString().split(":");
        String[] to = toTV.getText().toString().split(":");

        if (entryId == 0) {
            return entryService.insertEntry(entry_name, relayPin, switchId,
                    Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]),
                    Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]), isWeekDay, isMonth);
        } else {
            return entryService.updateEntryById(entryId, entry_name, relayPin, switchId,
                    Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]),
                    Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]), isWeekDay, isMonth);
        }
    }

    private void loadInfo(int entryId) {
        Entry entry = entryService.getEntryById(entryId);

        entryNameEV.setText(entry.getEntryName());
        fromTV.setText(entry.getStartTime());
        toTV.setText(entry.getEndTime());
        String[] isWeekDayArray = entry.getIsWeekDay().split(",");
        /*setToggleButton(isWeekDayArray[0],monBtn);
        setToggleButton(isWeekDayArray[1],tueBtn);
        setToggleButton(isWeekDayArray[2],wenBtn);
        setToggleButton(isWeekDayArray[3],thuBtn);
        setToggleButton(isWeekDayArray[4],friBtn);
        setToggleButton(isWeekDayArray[5],satBtn);
        setToggleButton(isWeekDayArray[6],sunBtn);*/
        for (int i = 0; i < 7; i++) {
            setToggleButton(isWeekDayArray[i], weekDayBtnList.get(i));
        }

        String[] isMonthArray = entry.getIsMonth().split(",");
        for (int i = 0; i < 12; i++) {
            setToggleButton(isMonthArray[i], monthBtnList.get(i));
        }
        /*setToggleButton(isMonth[0],janBtn);
        setToggleButton(isMonth[1],febBtn);
        setToggleButton(isMonth[2],marBtn);
        setToggleButton(isMonth[3],aprBtn);
        setToggleButton(isMonth[4],mayBtn);
        setToggleButton(isMonth[5],junBtn);
        setToggleButton(isMonth[6],julBtn);
        setToggleButton(isMonth[7],augBtn);
        setToggleButton(isMonth[8],sepBtn);
        setToggleButton(isMonth[9],octBtn);
        setToggleButton(isMonth[10],novBtn);
        setToggleButton(isMonth[11],decBtn);*/

    }

    private void setToggleButton(String str, ToggleButton btn) {
        if (str == "1") {
            btn.setChecked(true);
        } else {
            btn.setChecked(false);
        }
    }
}
