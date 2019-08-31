package com.cgme.POJO.PayPeriod;

public class TimeEntry {
    private int hours;
    private int minutes;

    public TimeEntry(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /** Returns the calculated time in fractional form. */
    public double getTotalTimeFractional(){
        return (this.hours) + ( (double)this.minutes / 60.0 );
    }
}
