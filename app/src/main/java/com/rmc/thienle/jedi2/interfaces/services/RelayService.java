package com.rmc.thienle.jedi2.interfaces.services;

import android.database.Cursor;

/**
 * Created by thien.lt on 11/16/2016.
 */

public interface RelayService {
    /**
     * Delete 1 or all(id = 0) records in Relay table.
     * @param id
     * @return
     */
    int deleteRelay (int id);
    Cursor getRelayById(int id);
    boolean insertRelay(String relay_name, int relay_pin, float preset_time);
    boolean updateRelay (String relay_name, int relay_pin, float preset_time);

}
