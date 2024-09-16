package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChildCareApplication extends Application {
    public static final String TITLE = "Bright Beginnings";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChildCareApplication.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}