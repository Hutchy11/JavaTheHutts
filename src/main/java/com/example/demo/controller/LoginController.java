package com.example.demo.controller;

import com.example.demo.model.*;
import javafx.fxml.FXML;

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

/**
 * Controller class for the Login View.
 * Handles the login process for both staff and carers.
 */
public class LoginController {

    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private Button signinButton;
    @FXML
    public IStaffDAO staffDAO;
    public ICarerDAO carerDAO;

    /**
     * Constructor for the LoginController.
     * Initializes the DAOs for staff and carer.
     */
    public LoginController() {
        this.staffDAO = new StaffDAO();
        this.carerDAO = new CarerDAO();
    }

    /**
     * Handles the login button click event.
     * Authenticates the user based on the provided email and password.
     *
     * @param event the action event triggered by the login button
     */
    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        Staff staff = staffDAO.login(email, password);
        if (staff != null) {
            showAlert("Login Successful", "Welcome, " + staff.getFullName() + " (Staff)");
            Session.setLoggedStaff(staff);
            loadDashboard(event, "InitialStaffView.fxml");
            return;
        }

        Carer carer = carerDAO.login(email, password);
        if (carer != null) {
            showAlert("Login Successful", "Welcome, " + carer.getFullName() + " (Carer)");
            Session.setLoggedCarer(carer);
            loadDashboard(event, "InitialCarerView.fxml");
            return;
        }

        // If neither staff nor carer was found, show login failed
        showAlert("Login Failed", "Invalid email or password.");
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
     * Loads a new FXML page after a successful login.
     *
     * @param event the action event triggered by the login button
     * @param fxmlFile the FXML file to load
     */
    private void loadDashboard(ActionEvent event, String fxmlFile) {
        try {
            // Get the current stage (window) and load the new FXML
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/" + fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the view.");
        }
    }
}