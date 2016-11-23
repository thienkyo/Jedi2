package com.rmc.thienle.jedi2.interfaces;

/**
 * Created by thien.lt on 11/16/2016.
 */

public interface Entry {
    String toRaw();
    String printOut();
    int getEntryId();
    String getEntryName();
    String getMonthWeekString();
    String getWorkingTime();
}
