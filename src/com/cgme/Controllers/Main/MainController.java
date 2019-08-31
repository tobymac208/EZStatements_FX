package com.cgme.Controllers.Main;

import com.cgme.Controllers.StatementCreator.StatementCreatorController;
import com.cgme._Statement.StatementTracker;
import javafx.fxml.FXML;
import com.cgme.FileOperations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;

import java.util.Objects;

/** Defines the class for controlling the main style and functionality of our application. */
public class MainController {
    private static StatementTracker statementTracker;

    @FXML
    TextArea dataArea;

    @FXML
    public void initialize(){
        // initialize our statement tracker
        statementTracker = new StatementTracker("Rio Home Services Pay");
        // load in the Statements
        statementTracker.setAllStatements(FileOperations.read_in_all_statements());
    }

    /** Prints out all data for the user to view. */
    public void printData(){
        // Set up a string to print to the data area
        String bufferedString = "";

        // Set our text box equal to our String that was returned.
        dataArea.setText(statementTracker.new_PrintStatementData());
    }

    /** Refreshes the Statement list */
    public void refreshStatementList(){
        // read in all of the statements again
        statementTracker.setAllStatements(FileOperations.read_in_all_statements());
        System.out.println("*Refreshed the list*");
    }

    /** Create a new Statements list */
    public void openCreationWindow(){
        
    }
}
