package com.cgme;

import com.cgme.Controllers.Main.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import com.cgme.POJO._Statement.StatementTracker;

public class Main extends Application {
    private static StatementTracker statementTracker = new StatementTracker("Rio Home Services Pay");

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Controllers/Main/MainController.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("EZStatements v1.0");
        primaryStage.setScene(new Scene(root, 750, 500));

        // Load in all of the data from the file
        statementTracker.setAllStatements(FileOperations.read_in_all_statements());

        // Pass statementTracker to the next window
        MainController controller = fxmlLoader.getController();
        controller.passTrackerByReference(statementTracker);

        // Open the new window
        primaryStage.showAndWait();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // Write data to the file
                FileOperations.write_data_to_file(statementTracker.getStatements());
            }
        });

    }

    @Override
    public void stop(){
        System.out.println("Application closing.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}