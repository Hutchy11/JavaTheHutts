package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.model.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InitialStaffViewController {

    @FXML
    private Label successLabel;

    public void initialize() {
        Staff loggedStaff = Session.getLoggedStaff();  // Retrieve the carer from the session

        if (loggedStaff != null) {
            // Set the label with the carer's full name and email
            successLabel.setText("Successful Carer Login: "
                    + loggedStaff.getFullName()
                    + " (" + loggedStaff.getEmail() + ")");
        } else {
            successLabel.setText("No Staff Logged In.");
        }
    }
}
