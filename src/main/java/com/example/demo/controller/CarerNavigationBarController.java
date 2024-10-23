package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Carer Navigation Bar.
 * Handles navigation between different views for carers.
 */
public class CarerNavigationBarController {

    @FXML
    private void navigateToCarerHome(ActionEvent event) {
        loadView(event, "InitialCarerView.fxml");
    }
    @FXML
    private void navigateToProfile(ActionEvent event) {
        loadView(event, "ProfileView.fxml");
    }
    @FXML
    private void navigateToViewRecipe(ActionEvent event) {
        loadView(event, "RecipeCarerView.fxml");
    }
    @FXML
    private void navigateToChildProfile(ActionEvent event) {
        loadView(event, "CarerChildProfileView.fxml");
    }
    @FXML
    private void navigateToSettings(ActionEvent event) {
        loadView(event, "CarerSettingsView.fxml");
    }

    @FXML
    private void logout(ActionEvent event) {
        // Clear session data or perform any necessary cleanup
        System.out.println("Logged out");

        // Redirect to the login view
        loadView(event, "LoginView.fxml");
    }

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
}