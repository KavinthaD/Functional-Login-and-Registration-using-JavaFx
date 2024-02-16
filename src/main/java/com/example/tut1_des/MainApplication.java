package com.example.tut1_des;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Login page");
        stage.setScene(scene);
        stage.show();

        // Set minimum width and height for the stage
        stage.setMinWidth(360);
        stage.setMinHeight(360);
    }
    public static void main(String[] args) {
        launch();
    }
}