package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.Session;
import com.example.demo.model.Staff;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

// Understand the elements used in the import and reuse in the screen.
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class CarerProfileMenuViewController {

    @FXML
    private void navigateToRegisterCarer(ActionEvent event) {
        loadView(event, "RegisterCarerView.fxml");
    }

    /*Keep for future expansion of page navigation */
    private void loadView(ActionEvent event, String fxmlFile) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/" + fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            //showAlert("Error", "Failed to load the view.");
        }
    }

    @FXML
    private void createPost(ActionEvent event) {
        // Implement the navigation logic here
        System.out.println("Creating Post");
    }
}