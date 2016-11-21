package com.rmc.thienle.jedi2.interfaces.services;

import com.rmc.thienle.jedi2.interfaces.Switch;

import java.util.ArrayList;

/**
 * Created by thien.lt on 11/16/2016.
 */

public interface SwitchService {

    /**
     * Delete 1 or all(id = 0) records in Switch table.
     * @param id switch_id col in table.
     * @return int. Number of affected rows
     */
    int deleteSwitchById(int id);
    int deleteAllSwitch();
    Switch getSwitchById(int id);
    ArrayList<Switch> getAllSwitch();
    boolean insertSwitch(String switch_name, String sync_code, String pass_code);
    boolean updateSwitchById (int switch_id, String switch_name, String sync_code, String pass_code);
}
