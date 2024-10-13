package com.example.demo.controller;

import com.example.demo.model.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import com.example.demo.model.Carer;
import com.example.demo.model.CarerDAO;

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

    public void initialize() {
        carerDAO = new CarerDAO();
        loggedCarer = Session.getLoggedCarer();
        loadUserData();
    }

    private void loadUserData() {
        if (loggedCarer != null) {
            phoneNumberField.setText(loggedCarer.getPhone());
            emailField.setText(loggedCarer.getEmail());
            addressField.setText(loggedCarer.getAddress());
        }
    }

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

    @FXML
    private void showPasswordResetSection() {
        passwordResetSection.setVisible(true);
    }

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}