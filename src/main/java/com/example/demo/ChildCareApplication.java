package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChildCareApplication extends Application {
    public static final String TITLE = "Bright Beginnings";
    private static Stage primaryStage;

    public static void loadScene(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChildCareApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Ensure the stage is maximized
        scene.getWindow().setWidth(primaryStage.getWidth()); // Set scene width to stage width
        scene.getWindow().setHeight(primaryStage.getHeight()); // Set scene height to stage height
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle(TITLE);
        primaryStage.setMaximized(true);
        loadScene("InitialStaffView.fxml");
        primaryStage.show();
    }
}