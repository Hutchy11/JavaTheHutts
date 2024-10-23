package com.example.demo.controller;

import com.example.demo.model.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import com.example.demo.model.Carer;
import com.example.demo.model.CarerDAO;

/**
 * Controller class for managing carer settings.
 */
public class CarerSettingsController {

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private VBox passwordResetSection;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField retypeNewPasswordField;

    private CarerDAO carerDAO;
    private Carer loggedCarer;

    /**
     * Initializes the controller and loads user data.
     */
    public void initialize() {
        carerDAO = new CarerDAO();
        loggedCarer = Session.getLoggedCarer();
        loadUserData();
    }


    /**
     * Loads the logged-in carer's data into the form fields.
     */
    private void loadUserData() {
        if (loggedCarer != null) {
            phoneNumberField.setText(loggedCarer.getPhone());
            emailField.setText(loggedCarer.getEmail());
            addressField.setText(loggedCarer.getAddress());
        }
    }

    /**
     * Updates the logged-in carer's data with the values from the form fields.
     */
    @FXML
    private void updateUserData() {
        if (loggedCarer != null) {
            loggedCarer.setPhone(phoneNumberField.getText());
            loggedCarer.setEmail(emailField.getText());
            loggedCarer.setAddress(addressField.getText());
            carerDAO.updateCarer(loggedCarer);
            showAlert("Success", "Your details have been successfully updated!");
        }
    }

    /**
     * Shows the password reset section.
     */
    @FXML
    private void showPasswordResetSection() {
        passwordResetSection.setVisible(true);
    }

    /**
     * Hides the password reset section.
     */
    @FXML
    private void hidePasswordResetSection() {
        passwordResetSection.setVisible(false);
    }

    @FXML
    private void resetPassword() {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String retypeNewPassword = retypeNewPasswordField.getText();

        if (!loggedCarer.getPassword().equals(currentPassword)) {
            showAlert("Error", "Current password is incorrect");
            return;
        }

        if (!newPassword.equals(retypeNewPassword)) {
            showAlert("Error", "New passwords do not match");
            return;
        }

        loggedCarer.setPassword(newPassword);
        carerDAO.updateCarer(loggedCarer);
        showAlert("Success", "Password reset successfully!");
        hidePasswordResetSection();
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
}