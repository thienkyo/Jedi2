package com.rmc.thienle.jedi2.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmc.thienle.jedi2.MainActivity;
import com.rmc.thienle.jedi2.R;
import com.rmc.thienle.jedi2.implementation.services.SwitchServiceImpl;
import com.rmc.thienle.jedi2.interfaces.Switch;
import com.rmc.thienle.jedi2.interfaces.services.SwitchService;

import java.util.List;

/**
 * Created by thien.lt on 11/28/2016.
 */

public class SwitchArrayAdapter extends ArrayAdapter<Switch> {
    private Context context;
    private int layoutResourceId;
    private List<Switch> switchList = null;
    private static SwitchService switchService;
    private Switch s;

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
        switchService = new SwitchServiceImpl(context);
        s = switchList.get(position);
        TextView switchNameTV = (TextView)convertView.findViewById(R.id.switch_list_name);
        switchNameTV.setText(switchList.get(position).getSwitchName());
        ImageView editIV = (ImageView) convertView.findViewById(R.id.item_info);

        //build switch add dialog
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View switchAddView = layoutInflater.inflate(R.layout.dialog_switch_add, null);
        final EditText switchNameED = (EditText) switchAddView.findViewById(R.id.dialog_switch_add_name);
        switchNameED.setText(s.getSwitchName());

        AlertDialog.Builder switchAddDialogBuilder = new AlertDialog.Builder(context);
        switchAddDialogBuilder.setView(switchAddView);
        switchAddDialogBuilder.setTitle("Switch creation");
        switchAddDialogBuilder
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        switchService.updateSwitchById(s.getSwitchId(),switchNameED.getText().toString(),s.getSyncCode(),s.getPassCode());
                        dialog.cancel();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return convertView;
    }
}
