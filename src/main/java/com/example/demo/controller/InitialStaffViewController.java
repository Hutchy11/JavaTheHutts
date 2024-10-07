package com.example.demo.controller;

import com.example.demo.model.Session;
import com.example.demo.model.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class InitialStaffViewController {

    @FXML
    private Label successLabel;

    public void initialize() {
        Staff loggedStaff = Session.getLoggedStaff();  // Retrieve the carer from the session

        if (loggedStaff != null) {
            // Set the label with the carer's full name and email
            successLabel.setText("Successful Staff Login: "
                    + loggedStaff.getFullName()
                    + " (" + loggedStaff.getEmail() + ")");
        } else {
            successLabel.setText("No Staff Logged In.");
        }
    }

    @FXML
    private void createPost(ActionEvent event) {
        // Implement the navigation logic here
        System.out.println("Creating Post");
    }
}