package com.rmc.thienle.jedi2.interfaces.services;

import android.database.Cursor;

/**
 * Created by thien.lt on 11/16/2016.
 */

public interface SwitchService {

    /**
     * Delete 1 or all(id = 0) records in Switch table.
     * @param id
     * @return int. Number of affected rows
     */
    int deleteSwitch (int id);
    Cursor getSwitchById(int id);
    boolean insertSwitch(String switch_name, String sync_code, String pass_code);
    boolean updateSwitchById (int switch_id, String switch_name, String sync_code, String pass_code);
}
