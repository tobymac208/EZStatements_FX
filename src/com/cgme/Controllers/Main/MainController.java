package com.cgme.Controllers.Main;

import com.cgme.Controllers.StatementCreator.StatementCreatorController;
import com.cgme.POJO._Statement.StatementTracker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import com.cgme.FileOperations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/** Defines the class for controlling the main style and functionality of our application. */
public class MainController {
    private static StatementTracker statementTracker;

    @FXML
    TextArea dataArea;

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
        System.out.println("*Refreshed the list*");
    }

    /** Create a new Statements list */
    @FXML
    void openCreationWindowAction(){
        try{
            // Load an FXML file, using a specified resource
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../StatementCreator/StatementCreatorController.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            StatementCreatorController statementCreatorController = fxmlLoader.getController();
            statementCreatorController.passInStatementTracker(statementTracker);

            // Create a stage to work with
            Stage stage = new Stage();

            // Set the modality -- how this window interacts with other windows
            stage.initModality(Modality.APPLICATION_MODAL);

            // Set a title for the new window
            stage.setTitle("Create new Statements list");

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
}
