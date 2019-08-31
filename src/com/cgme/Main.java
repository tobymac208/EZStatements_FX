package com.cgme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Controllers/Main/MainController.fxml"));
        primaryStage.setTitle("EZStatement v1.0");
        primaryStage.setScene(new Scene(root, 550, 750));
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println("Application closing.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}