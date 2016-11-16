package com.rmc.thienle.jedi2.interfaces.services;

/**
 * Created by thien.lt on 11/16/2016.
 */

public interface EntryService {
    public boolean insertEntry(String entry_name, int start_hr,int start_min, int start_sec,int end_hr,int end_min, int end_sec,
                               String is_weekday, String is_month, int relay_pin, int switch_id);

}
