package com.rmc.thienle.jedi2.interfaces.services;

import com.rmc.thienle.jedi2.interfaces.Relay;

import java.util.List;

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
    int deleteAllRelay ();
    Relay getRelayById(int id);
    List<Relay> getAllRelay();
    List<Relay> getRelayBySwitchId(long switchId);
    boolean insertRelay(String relay_name, int relay_pin, float preset_time);
    boolean updateRelay (String relay_name, int relay_pin, float preset_time);
}
