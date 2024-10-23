package com.example.demo.controller;

import com.example.demo.model.StaffDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Register Staff View.
 * Handles the registration of a new staff member.
 */
public class RegisterStaffViewController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField roleField;

    @FXML
    private TextField hireDateField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    private StaffDAO staffDAO;

    /**
     * Constructor for the RegisterStaffViewController.
     * Initializes the StaffDAO.
     */
    public RegisterStaffViewController() {
        staffDAO = new StaffDAO();
    }

    /**
     * Handles the registration process when the register button is clicked.
     * Validates the input fields and registers the staff member if valid.
     *
     * @param event the action event triggered by the register button
     */
    @FXML
    private void handleRegister(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String role = roleField.getText();
        String hireDate = hireDateField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }

        String staffId = java.util.UUID.randomUUID().toString(); // Generate a unique ID for the carer

        boolean success = staffDAO.registerStaff(staffId, firstName, lastName, email, password, phone, role, hireDate);

        if (success) {
            showAlert("Success", "Staff member registered successfully.");
            clearFields();
            navigateToStaffProfileMenu(event);
        } else {
            showAlert("Error", "Failed to register staff member.");
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
        roleField.clear();
        hireDateField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    /**
     * Navigates to the Staff Profile Menu view after successful registration.
     *
     * @param event the action event triggered by the register button
     */
    private void navigateToStaffProfileMenu(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/StaffProfileMenuView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Staff Profile Menu view.");
        }
    }

}