package com.cgme.Controllers.Main;

import com.cgme.Controllers.StatementCreator.StatementCreatorController;
import com.cgme.POJO._Statement.StatementTracker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import com.cgme.FileOperations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.text.ParseException;

/** Defines the class for controlling the main style and functionality of our application. */
public class MainController {
    private static StatementTracker statementTracker;

    @FXML
    TextArea dataArea;
    @FXML
    TextField numberToFlip;
    @FXML
    Label errorLabel;

    public MainController(){
        // initialize our statement tracker
        statementTracker = new StatementTracker("Rio Home Services Pay");
        // load in the Statements
        statementTracker.setAllStatements(FileOperations.read_in_all_statements());
    }

    /** Prints out all data for the user to view. */
    @FXML
    void printDataAction(){
        // Set up a string to print to the data area
        String bufferedString = "";

        // Set our text box equal to our String that was returned.
        dataArea.setText(statementTracker.new_PrintStatementData());
    }

    /** Refreshes the Statement list */
    @FXML
    void refreshStatementListAction(){
        // read in all of the statements again
        statementTracker.setAllStatements(statementTracker.getStatements());

        errorLabel.setText("*Refreshed the list*");
    }

    /** Create a new Statements list */
    @FXML
    void openCreationWindowAction(){
        try{
            // Load an FXML file, using a specified resource
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../StatementCreator/StatementCreatorController.fxml"));
            Parent root = fxmlLoader.load();

            StatementCreatorController statementCreatorController = fxmlLoader.getController();
            statementCreatorController.passInStatementTracker(statementTracker);

            // Create a stage to work with
            Stage stage = new Stage();
            stage.setHeight(750);
            stage.setWidth(550);

            // Set the modality -- how this window interacts with other windows
            stage.initModality(Modality.APPLICATION_MODAL);

            // Set a title for the new window
            stage.setTitle("Create new statement");

            // Set the scene for the stage
            stage.setScene(new Scene(root));

            // Show the new window
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /** Called to close the application */
    @FXML
    void exitApplicationAction(){
        // Write data to the file
        FileOperations.write_data_to_file(statementTracker.getStatements());

        Platform.exit(); // call this method to trigger the stop() call
    }

    /** Flips a specified "consolidated" value */
    @FXML
    void flipBoolean(){
        String numberChosenToFlip = numberToFlip.getText();
        int actualNumber;

        if(numberChosenToFlip.length() >= 1){
            try {
                actualNumber = Integer.parseInt(numberChosenToFlip);
            }catch (Exception e){
                errorLabel.setText("Invalid entry.");
                return;
            }
        }else{
            // exit the function
            errorLabel.setText("Nothing was entered.");
            return;
        }

        // Is that a valid number?
        if(actualNumber > statementTracker.getStatements().getStatements().size() - 1 || actualNumber < 0){
            errorLabel.setText("Invalid number.");
        }else{
            boolean val = statementTracker.getStatements().getStatements().get(actualNumber).isConsolidated();
            statementTracker.getStatements().getStatements().get(actualNumber).setConsolidated(!val); // flips the value
            errorLabel.setText("Boolean value (index " + actualNumber + ") successfully changed to " + !val);
            printDataAction();
        }
    }
}
