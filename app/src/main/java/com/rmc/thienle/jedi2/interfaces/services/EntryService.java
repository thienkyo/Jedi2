package com.rmc.thienle.jedi2.interfaces.services;

import com.rmc.thienle.jedi2.interfaces.Entry;

import java.util.ArrayList;

/**
 * Created by thien.lt on 11/16/2016.
 */

public interface EntryService {
    /**
     * Delete 1 or all(id = 0) records in Entry table.
     * @param id entry_id in ENTRY table
     * @return number of record affected.
     */
    int deleteEntryById(int id);
    int deleteAllEntry();
    int deleteEntryBySwitchId(int switchId);
    int deleteEntryByRelayPin(int relayPin);
    boolean insertEntry(String entry_name, int start_hr,int start_min, int start_sec,int end_hr,int end_min, int end_sec,
                               String is_weekday, String is_month, int relay_pin, int switch_id);
    boolean updateEntryById(int entry_id,String entry_name, int start_hr,int start_min, int start_sec,int end_hr,int end_min, int end_sec,
                                String is_weekday, String is_month, int relay_pin, int switch_id);
    ArrayList<Entry> getAllEntryBySwitchId(int switchId);
    ArrayList<Entry> getAllEntryByRelayPin(int relayPin);
    Entry getEntryById(int id);
    ArrayList<Entry> getAllEntry();
}
