package com.rmc.thienle.jedi2.interfaces;

/**
 * Created by thien.lt on 11/16/2016.
 */

public interface Entry {
    String toTransmitString();
    String printOut();
    int getEntryId();
    String getEntryName();
    String getMonthWeekString();
    String getWorkingTime();
    String getStartTime();
    String getEndTime();
    String getIsWeekDay();
    String getIsMonth();
}
