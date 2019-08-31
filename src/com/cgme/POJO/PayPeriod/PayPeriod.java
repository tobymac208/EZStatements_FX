package com.cgme.POJO.PayPeriod;

public class PayPeriod {
    private TimeEntryList timeEntryList;
    private double payRate;

    public PayPeriod(TimeEntryList timeEntryList, double payRate) {
        this.timeEntryList = timeEntryList;
        this.payRate = payRate;
    }

    public TimeEntryList getTimeEntryList() {
        return timeEntryList;
    }

    public void setTimeEntryList(TimeEntryList timeEntryList) {
        this.timeEntryList = timeEntryList;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    /** Calculate the pay for this specific period */
    public double calculatePayForPeriod(){
        return timeEntryList.getTotalTime() * payRate;
    }
}
