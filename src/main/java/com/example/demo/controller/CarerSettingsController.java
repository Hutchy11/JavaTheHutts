package com.example.demo.controller;

import com.example.demo.model.Session;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.example.demo.model.Carer;
import com.example.demo.model.CarerDAO;

public class CarerSettingsController {

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    private CarerDAO carerDAO;
    private Carer loggedCarer;

    public void initialize() {
        carerDAO = new CarerDAO();
        loggedCarer = Session.getLoggedCarer(); // Use the class-level variable
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
            carerDAO.updateCarer(loggedCarer); // Implement this method to update the user data in the database
        }
    }
}