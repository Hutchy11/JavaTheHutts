package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.model.Session;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Label;

import java.io.IOException;


public class InitialStaffViewController {

    @FXML
    private Label successLabel;

    public void initialize() {
        Staff loggedStaff = Session.getLoggedStaff();  // Retrieve the Staff from the session

        if (loggedStaff != null) {
            // Set the label with the Staff's full name and email
            successLabel.setText("Successful Staff Login: "
                    + loggedStaff.getFullName()
                    + " (" + loggedStaff.getEmail() + ")");
        } else {
            successLabel.setText("No Staff Logged In.");
        }
    }


    @FXML
    private void createChildProfile() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/ChildProfileForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create Child Profile");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
