package com.example.demo.controller;

import com.example.demo.model.CarerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;

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

    public RegisterCarerViewController() {
        carerDAO = new CarerDAO();
    }

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
        } else {
            showAlert("Error", "Failed to register carer.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

/*  Old form check code.
    if (success) {
            showAlert("Success", "Carer registered successfully.");
        } else {
            showAlert("Error", "Failed to register carer.");
        }
    }*/