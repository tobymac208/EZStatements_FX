package com.cgme.Controllers.StatementCreator.PayPeriodCreator;

import com.cgme.POJO.PayPeriod.PayPeriod;
import com.cgme.POJO.PayPeriod.TimeEntry;
import com.cgme.POJO.PayPeriod.TimeEntryList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PayPeriodCreatorController {
    private PayPeriod referenceToPayPeriod;

    @FXML
    TextField sunday_hours;
    @FXML
    TextField sunday_minutes;

    @FXML
    TextField monday_hours;
    @FXML
    TextField monday_minutes;

    @FXML
    TextField tuesday_hours;
    @FXML
    TextField tuesday_minutes;

    @FXML
    TextField wednesday_hours;
    @FXML
    TextField wednesday_minutes;

    @FXML
    TextField thursday_hours;
    @FXML
    TextField thursday_minutes;

    @FXML
    TextField friday_hours;
    @FXML
    TextField friday_minutes;

    @FXML
    TextField saturday_hours;
    @FXML
    TextField saturday_minutes;

    @FXML
    TextField payRate;

    @FXML
    void calculatePayPeriod(){
        // Make sure all fields have something in them
        if(sunday_hours.getText().trim().length() >= 1 && sunday_minutes.getText().trim().length() >= 1 && monday_hours.getText().trim().length() >= 1
        && monday_minutes.getText().trim().length() >= 1 && tuesday_hours.getText().trim().length() >= 1 && tuesday_minutes.getText().trim().length() >= 1
        && wednesday_hours.getText().trim().length() >= 1 && wednesday_minutes.getText().trim().length() >= 1 && thursday_hours.getText().trim().length() >= 1
        && thursday_minutes.getText().trim().length() >= 1 && friday_hours.getText().trim().length() >= 1 && friday_minutes.getText().trim().length() >= 1
        && saturday_hours.getText().trim().length() >= 1 && saturday_minutes.getText().trim().length() >= 1 && payRate.getText().trim().length() >= 1){
            // Time entry for Sunday
            TimeEntry sunday_entry = new TimeEntry(Integer.parseInt(sunday_hours.getText()), Integer.parseInt(sunday_minutes.getText()));
            // Time entry for Monday
            TimeEntry monday_entry = new TimeEntry(Integer.parseInt(monday_hours.getText()), Integer.parseInt(monday_minutes.getText()));
            // Time entry for Tuesday
            TimeEntry tuesday_entry = new TimeEntry(Integer.parseInt(tuesday_hours.getText()), Integer.parseInt(tuesday_minutes.getText()));
            // Time entry for Wednesday
            TimeEntry wednesday_entry = new TimeEntry(Integer.parseInt(wednesday_hours.getText()), Integer.parseInt(wednesday_minutes.getText()));
            // Time entry for Thursday
            TimeEntry thursday_entry = new TimeEntry(Integer.parseInt(thursday_hours.getText()), Integer.parseInt(thursday_minutes.getText()));
            // Time entry for Friday
            TimeEntry friday_entry = new TimeEntry(Integer.parseInt(friday_hours.getText()), Integer.parseInt(friday_minutes.getText()));
            // Time entry for Saturday
            TimeEntry saturday_entry = new TimeEntry(Integer.parseInt(saturday_hours.getText()), Integer.parseInt(saturday_minutes.getText()));

            // Add all of the items to a list
            TimeEntryList entryList = new TimeEntryList();
            entryList.addTimeEntry(saturday_entry);
            entryList.addTimeEntry(monday_entry);
            entryList.addTimeEntry(tuesday_entry);
            entryList.addTimeEntry(wednesday_entry);
            entryList.addTimeEntry(thursday_entry);
            entryList.addTimeEntry(friday_entry);
            entryList.addTimeEntry(saturday_entry);

            referenceToPayPeriod.setTimeEntryList(entryList);
        }
    }

    public void passInReferenceToPayPeriod(PayPeriod thePayPeriod){
        referenceToPayPeriod = thePayPeriod;
    }
}
