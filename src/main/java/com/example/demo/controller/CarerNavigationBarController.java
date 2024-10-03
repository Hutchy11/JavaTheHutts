package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        loadView(event, "RecipeView.fxml");
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
        // Implement logout logic here
        System.out.println("Logged out");
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

    /* Figure out if this is worth keeping.
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    } */
}