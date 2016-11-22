package com.rmc.thienle.jedi2.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.rmc.thienle.jedi2.interfaces.Entry;

import java.util.List;

/**
 * Created by thien.lt on 11/22/2016.
 */

public class EntryArrayAdapter extends ArrayAdapter {
    public EntryArrayAdapter(Context context, int resource, List<Entry> entryList) {
        super(context, resource, entryList);
    }
}
