package com.rmc.thienle.jedi2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.rmc.thienle.jedi2.adapter.EntryArrayAdapter;
import com.rmc.thienle.jedi2.implementation.EntryImpl;
import com.rmc.thienle.jedi2.implementation.RelayImpl;
import com.rmc.thienle.jedi2.implementation.services.EntryServiceImpl;
import com.rmc.thienle.jedi2.implementation.services.RelayServiceImpl;
import com.rmc.thienle.jedi2.implementation.services.SwitchServiceImpl;
import com.rmc.thienle.jedi2.interfaces.Entry;
import com.rmc.thienle.jedi2.interfaces.Relay;
import com.rmc.thienle.jedi2.interfaces.Switch;
import com.rmc.thienle.jedi2.interfaces.services.EntryService;
import com.rmc.thienle.jedi2.interfaces.services.RelayService;
import com.rmc.thienle.jedi2.interfaces.services.SwitchService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static EntryService entryService;
    private static RelayService relayService;
    private static SwitchService switchService;
    private int switchId;
    ArrayAdapter switchsAdapter;
    ListView switchLV;
    List<Switch> switchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle(R.string.status_not_connect);
        setSupportActionBar(toolbar);

        entryService = new EntryServiceImpl(this);
        relayService = new RelayServiceImpl(this);
        switchService = new SwitchServiceImpl(this);
        // first time load;
        switchId = 1;
        Switch defaultSwitch = switchService.getSwitchById(switchId);
        toolbar.setTitle(defaultSwitch.getSwitchName());
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), switchId);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                if(entryList.size() <=20) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", 0);
                    bundle.putString("val", "");
                    Intent myIntent = new Intent(MainActivity.this, ThirdActivity.class);
                    myIntent.putExtra("MyPackage", bundle);
                    startActivity(myIntent);
                }else{
                    Snackbar.make(view, R.string.max_number_entry, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }*/
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_switch_list) {
            //  chksys();
            return true;
        }

        if (id == R.id.action_check_system) {
            //  chksys();
            return true;
        }
        if (id == R.id.action_sync) {
/*
            progressBarHandler = new Handler();
            entry_Index = 0;
            progress = new ProgressDialog(this); // this = YourActivity
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setMessage("Syncing. Please wait...");
            progress.setProgress(0);
            progress.setMax(entryList.size());
            progress.setIndeterminate(false);
            progress.setCanceledOnTouchOutside(false);
            progress.show();

            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    // do the thing that takes a long time
                    try {
                        mBtService.sync(entryList);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "write data to bluetooth device in MainActivity 1234");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            progress.dismiss();
                        }
                    });
                }
            }).start();*/
            return true;
        }
        if (id == R.id.action_sync_time) {
            //   syncTime();

            return true;
        }
        if (id == R.id.action_connect) {
/*            // mBtService.stop();
            String temp_addr = getLastBludeviceAddr();
            Log.d(TAG, "address:"+temp_addr);
            if(mBtService.getState() != BluetoothService.STATE_CONNECTED) {
                if (temp_addr == "") { // last device : no and paired devices : no
                    mBluetoothAdapter.startDiscovery();
                    showBluetoothDialog();
                    //item.setIcon(R.drawable.ic_bluetooth_enable);
                } else { //last device : yes
                    connect(temp_addr, true);
                }
            }*/
            return true;
        }
        if (id == R.id.action_delete_last_device) {
            //  manageLastDevice("");
            return true;
        }
        if (id == R.id.action_add_fake_last_device) {
            //   manageLastDevice("20:16:01:06:79:99");
            return true;
        }
        if (id == R.id.action_delete_all) {
            deleteAllEntry();
            return true;
        }
        if (id == R.id.action_add_item) {
            addSampleEntry();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addSampleEntry() {
        entryService.insertEntry("sang60", 9, 30, 0, 9, 35, 20, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 6, 0);
        entryService.insertEntry("trua60", 12, 30, 0, 12, 35, 20, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 6, 0);
        entryService.insertEntry("Chieu60", 15, 55, 0, 15, 59, 15, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 6, 0);

        entryService.insertEntry("sang70", 9, 30, 0, 9, 35, 20, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 7, 0);
        entryService.insertEntry("trua70", 12, 30, 0, 12, 35, 20, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 7, 0);
        entryService.insertEntry("Chieu70", 15, 55, 0, 15, 59, 15, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 7, 0);

        entryService.insertEntry("sang61", 9, 30, 0, 9, 35, 20, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 6, 1);
        entryService.insertEntry("trua61", 12, 30, 0, 12, 35, 20, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 6, 1);
        entryService.insertEntry("Chieu61", 15, 55, 0, 15, 59, 15, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 6, 1);

        entryService.insertEntry("sang71", 9, 30, 0, 9, 35, 20, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 7, 1);
        entryService.insertEntry("trua71", 12, 30, 0, 12, 35, 20, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 7, 1);
        entryService.insertEntry("Chieu71", 15, 55, 0, 15, 59, 15, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1", 7, 1);
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    private void showSwitchList() {
        switchList = switchService.getAllSwitch();
        List<String> strSwitches = new ArrayList<>();
        for (Switch temp : switchList) {
            strSwitches.add(temp.getSwitchName());
        }
        switchsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strSwitches);

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View contentView = layoutInflater.inflate(R.layout.dialog_switch_list, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(contentView);
        alertDialogBuilder.setTitle("Switch list");

        switchLV = (ListView) contentView.findViewById(R.id.switch_list);
        switchLV.setAdapter(switchsAdapter);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create an alert dialog
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        switchLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switchId = switchList.get(arg2).getSwitchId();
                alert.cancel();
            }
        });
    }

    private void deleteAllEntry() {
        entryService.deleteAllEntry();
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "outlet_number";
        private static final String ARG_SWITCH_ID = "switchId";
        private List<Entry> entryList;
        private List<Relay> allRelayList;

        private List<Switch> switchList;
        private EntryArrayAdapter entryAdapter;
        //private EntryService es;
        //private RelayService rs;
        //private SwitchService ss;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, int switchId) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_SWITCH_ID, switchId);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int outletNum = getArguments().getInt(ARG_SECTION_NUMBER);
            int switchId = getArguments().getInt(ARG_SWITCH_ID);
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final TextView resultTV = (TextView) rootView.findViewById(R.id.section_label);
            TextView tempTV = (TextView) rootView.findViewById(R.id.temp_label);
            resultTV.setText(getString(R.string.section_format, outletNum + 5));
            ListView entryLV = (ListView) rootView.findViewById(R.id.entry_list);

            //es = new EntryServiceImpl(getContext());
            //rs = new RelayServiceImpl(getContext());
            //ss = new SwitchServiceImpl(getContext());
            //entryList = es.getAllEntry();
            //1. create ArrayList object: entry
            //entryList = MainActivity.entryService.getAllEntry();
            entryList = MainActivity.entryService.getAllEntryBySwitchIdRelayPin(switchId, outletNum + 5);
            allRelayList = MainActivity.relayService.getAllRelay();
            switchList = MainActivity.switchService.getAllSwitch();

            String relayString = "";
            for (Relay r : allRelayList) {
                relayString += r.printOut() + "\n";
            }
            String switchString = "";
            for (Switch s : switchList) {
                switchString += s.printOut() + "\n";
            }
            tempTV.setText(switchString + "\n" + relayString);

            if (entryList == null) {
                entryList = new ArrayList<>();
                Entry temp = new EntryImpl("No data", 0, 0, 0, 0, 0, 0, 0, 0, 0, "1,1,1,1,1,1,1", "1,1,1,1,1,1,1,1,1,1,1,1");
                entryList.add(temp);
            }
            //2. input Data Source (ArrayList object) into ArrayAdapter
            entryAdapter = new EntryArrayAdapter(getContext(), R.layout.entry_list_item_layout, entryList);
            //3. set Adapter for ListView
            entryLV.setAdapter(entryAdapter);
            //4. handle if user pick one item in ListView
            entryLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    resultTV.setText("position : " + arg2 + "; value =" + entryList.get(arg2));
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", entryList.get(arg2).getEntryId());
                    bundle.putString("val", entryList.get(arg2).toRaw());
                    Intent myIntent = new Intent(getContext(), EntryManageActivity.class);
                    myIntent.putExtra("MyBundle", bundle);
                    startActivity(myIntent);
                }
            });
            //5. handle Long click event
            entryLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    Log.d(TAG, "entry : " + entryList.get(arg2).printOut());
                    //  entryDB.deleteEntryById(entryList.get(arg2).getEntryId());
                    MainActivity.entryService.deleteEntryById(entryList.get(arg2).getEntryId());
                    entryAdapter.remove(entryList.get(arg2));
                    entryAdapter.notifyDataSetChanged();
                    return true;
                }
            });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private int switchId;
        private List<Relay> relayList;
        private int count;

        public SectionsPagerAdapter(FragmentManager fm, int switchId) {
            super(fm);
            this.switchId = switchId;
            relayList = MainActivity.relayService.getRelayBySwitchId(switchId);
            if (relayList == null) {
                relayList = new ArrayList<>();
                relayList.add(new RelayImpl("no relay", 0, 0, 0));
            }
            count = relayList.size();
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1, switchId);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return count;
        }

        @Override
        public int getItemPosition(Object object) {
            // Causes adapter to reload all Fragments when
            // notifyDataSetChanged is called
            return POSITION_NONE;
            //return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return relayList.get(position).getRelayName();
        /*
            switch (position) {
                case 0:
                    return "OUTLET 1(pin 6)";
                case 1:
                    return "OUTLET 2(pin 7)";
            }
            return null;*/
        }
    }
}
