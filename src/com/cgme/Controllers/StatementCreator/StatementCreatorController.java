package com.cgme.Controllers.StatementCreator;

import com.cgme._Statement.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StatementCreatorController {
    private Statement statement;

    // FXML variables
    @FXML
    TextField name;
    @FXML
    RadioButton is_consolidated;
    @FXML
    TextField price;
    @FXML
    Button closeButton;

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

    /** Used for finishing up with this window and returning the statements list to the caller. */
    @FXML
    void finishUpAction(){
        // Make use the Statement isn't null
        if(statement != null) {
            exitAction(); // close the window
        }
    }

    private void clearFields(){
        name.setText("");
        is_consolidated.setSelected(false);
        price.setText("");
    }
}
