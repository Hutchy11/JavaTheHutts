package com.example.demo.controller;

import com.example.demo.model.CarerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Controller class for the Register Carer View.
 * Handles the registration of a new carer.
 */
public class RegisterCarerViewController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    private CarerDAO carerDAO;

    /**
     * Constructor for the RegisterCarerViewController.
     * Initializes the CarerDAO.
     */
    public RegisterCarerViewController() {
        carerDAO = new CarerDAO();
    }

    /**
     * Handles the registration process when the register button is clicked.
     * Validates the input fields and registers the carer if valid.
     *
     * @param event the action event triggered by the register button
     */
    @FXML
    private void handleRegister(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }

        String carerId = java.util.UUID.randomUUID().toString(); // Generate a unique ID for the carer

        boolean success = carerDAO.registerCarer(carerId, firstName, lastName, email, password, phone, address);

        if (success) {
            showAlert("Success", "Carer registered successfully.");
            clearFields();
            navigateToCarerProfileMenu(event);
        } else {
            showAlert("Error", "Failed to register carer.");
        }
    }

    /**
     * Shows an alert with the specified title and message.
     *
     * @param title the title of the alert
     * @param message the message of the alert
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Clears all input fields in the registration form.
     */
    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    /**
     * Navigates to the Carer Profile Menu view after successful registration.
     *
     * @param event the action event triggered by the register button
     */
    private void navigateToCarerProfileMenu(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/CarerProfileMenuView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Carer Profile Menu view.");
        }
    }
}

/*  Old form check code.
    if (success) {
            showAlert("Success", "Carer registered successfully.");
        } else {
            showAlert("Error", "Failed to register carer.");
        }
    }*/