package com.rmc.thienle.jedi2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rmc.thienle.jedi2.R;
import com.rmc.thienle.jedi2.interfaces.Switch;

import java.util.List;

/**
 * Created by thien.lt on 11/28/2016.
 */

public class SwitchArrayAdapter extends ArrayAdapter<Switch> {
    private Context context;
    private int layoutResourceId;
    private List<Switch> switchList = null;

    public SwitchArrayAdapter(Context context, int layoutResourceId, List<Switch> objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.switchList = objects;
    }

    @Override
    public int getCount() {
        return switchList.size();
    }

    @Override
    public Switch getItem(int position) {
        return switchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return switchList.get(position).getSwitchId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dialog_switch_list_item_layout, null);
        }

        TextView switchNameTV = (TextView)convertView.findViewById(R.id.switch_list_name);
        switchNameTV.setText(switchList.get(position).getSwitchName());
        return convertView;
    }
}
