package com.rmc.thienle.jedi2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rmc.thienle.jedi2.R;
import com.rmc.thienle.jedi2.interfaces.Entry;

import java.util.List;

/**
 * Created by thien.lt on 11/22/2016.
 */

public class EntryArrayAdapter extends ArrayAdapter<Entry> {
    private Context context;
    private int layoutResourceId;
    private List<Entry> entryList = null;

    public EntryArrayAdapter(Context context, int layoutResourceId, List<Entry> entryList) {
        super(context, layoutResourceId, entryList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.entryList = entryList;
    }

    @Override
    public int getCount() {
        return entryList.size();
    }

    @Override
    public Entry getItem(int position) {
        return entryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return entryList.get(position).getEntryId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.entry_list_item_layout, null);
        }

        TextView entryNameTV = (TextView)convertView.findViewById(R.id.entry_name);
        TextView monthWeekTV=(TextView)convertView.findViewById(R.id.month_week);
        TextView workingTimeTV=(TextView)convertView.findViewById(R.id.entry_time);

        entryNameTV.setText(entryList.get(position).getEntryName());
        monthWeekTV.setText(entryList.get(position).getMonthWeekString());
        workingTimeTV.setText(entryList.get(position).getWorkingTime());

        return convertView;
    }
}
