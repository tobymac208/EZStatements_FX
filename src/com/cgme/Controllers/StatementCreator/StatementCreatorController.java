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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StatementCreatorController {
    private Statement statement;
    private StatementTracker field_statementTracker;
    private PayPeriod payPeriod;

    // FXML variables
    @FXML
    TextField name;
    @FXML
    RadioButton is_consolidated;
    @FXML
    TextField price;
    @FXML
    Button closeButton;

    public StatementCreatorController(){
        this.payPeriod = new PayPeriod();
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
        if(name.getText().trim().length() >= 2 && price.getText().trim() .length() >= 1){
            // Process the data
            String field_name = name.getText();
            boolean field_is_consolidated = is_consolidated.isSelected();
            // TODO: Deal with application crashing
            double field_price = Double.parseDouble(price.getText());

            statement = new Statement(field_name, field_is_consolidated, field_price);
            clearFields();
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
        name.setText("");
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

            price.setText(String.valueOf(Utility.round_double(payPeriod.calculatePayForPeriod())));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
