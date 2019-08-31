package com.cgme.Controllers.StatementCreator;

import com.cgme.Controllers.StatementCreator.PayPeriodCreator.PayPeriodCreatorController;
import com.cgme.POJO.PayPeriod.PayPeriod;
import com.cgme.POJO._Statement.Statement;
import com.cgme.POJO._Statement.StatementTracker;
import com.cgme.Utility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class StatementCreatorController {
    private Statement statement;
    private StatementTracker field_statementTracker;
    private PayPeriod payPeriod;

    // FXML variables
    @FXML
    DatePicker datePicker;
    @FXML
    RadioButton is_consolidated;
    @FXML
    TextField price;
    @FXML
    Button closeButton;
    @FXML
    Button finishedButton;
    @FXML
    Button addStatementButton;

    public StatementCreatorController(){
        this.payPeriod = new PayPeriod();
    }

    /** Preliminary actions to run */
    @FXML
    public void initialize(){
        // Disable the button
        finishedButton.setDisable(true);

        datePicker.setEditable(false);
    }

    public void passInStatementTracker(StatementTracker statementTracker){
        field_statementTracker = statementTracker; // passed by reference!
    }

    @FXML
    void exitAction(){
        // Get a handle for the stage
        Stage stage = (Stage)closeButton.getScene().getWindow();
        // close the window
        stage.close();
    }

    /** Used for creating a new statement to add to the list. */
    @FXML
    void addNewStatementAction(){
        // Make use the fields have something in them
        if(price.getText().trim() .length() >= 1 && datePicker.getValue() != null){
            // Get the DatePicker information
            LocalDate fieldName = datePicker.getValue();
            System.out.println("field name = " + fieldName.toString());
            // Convert to the preferred style
            String[] fieldNamesStrings = fieldName.toString().split("-");
            String year = fieldNamesStrings[0], month = fieldNamesStrings[1], day = fieldNamesStrings[2];
            String formattedString = month + "/" + day + "/" + year;

            // Boolean for "is consolidated"
            boolean field_is_consolidated = is_consolidated.isSelected();

            // TODO: Deal with application crashing
            double field_price = Double.parseDouble(price.getText());

            statement = new Statement(formattedString, field_is_consolidated, field_price);
            clearFields();
            finishedButton.setDisable(false);
        }
    }

    /** Used for finishing up with this window and returning the statement to the caller. */
    @FXML
    void finishUpAction(){
        // Make sure the Statement isn't null
        if(statement != null) {
            field_statementTracker.addStatement(statement);
            exitAction(); // close the window
        }
    }

    private void clearFields(){
        datePicker.getEditor().clear();
        is_consolidated.setSelected(false);
        price.setText("");
    }

    @FXML
    public void addPayPeriodAction(){
        try{
            // Load an FXML file, using a specified resource
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PayPeriodCreator/PayPeriodCreatorController.fxml"));
            Parent root = fxmlLoader.load();

            PayPeriodCreatorController payPeriodCreatorController = fxmlLoader.getController();
            payPeriodCreatorController.passInReferenceToPayPeriod(payPeriod);

            // Create a stage to work with
            Stage stage = new Stage();
            stage.setHeight(750);
            stage.setWidth(550);

            // Set the modality -- how this window interacts with other windows
            stage.initModality(Modality.APPLICATION_MODAL);

            // Set a title for the new window
            stage.setTitle("Create new pay period");

            // Set the scene for the stage
            stage.setScene(new Scene(root));

            // Show the new window
            stage.showAndWait();

            if(payPeriod.getTimeEntryList() == null){
                System.out.println("The creation failed.");
            }else{
                price.setText(String.valueOf(Utility.round_double(payPeriod.calculatePayForPeriod())));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
