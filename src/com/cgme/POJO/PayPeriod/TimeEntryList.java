package com.cgme.POJO.PayPeriod;

import java.util.ArrayList;

public class TimeEntryList {
    private ArrayList<TimeEntry> timeEntries;

    public TimeEntryList(ArrayList<TimeEntry> timeEntries) {
        this.timeEntries = new ArrayList<>();
    }

    public ArrayList<TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    public void setTimeEntries(ArrayList<TimeEntry> timeEntries) {
        this.timeEntries = timeEntries;
    }

    public void addTimeEntry(TimeEntry value){
        timeEntries.add(value);
    }

    /** Returns the total time from all of the time entries. */
    public double getTotalTime(){
        double totalTime = 0.0;

        for(TimeEntry entry : timeEntries){
            totalTime += entry.getTotalTimeFractional();
        }

        return totalTime;
    }
}
