package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

// Understand the elements used in the import and reuse in the screen.
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


public class InitialCarerViewController {

    @FXML
    private Label successLabel;

    public void initialize() {
        Carer loggedCarer = Session.getLoggedCarer();  // Retrieve the carer from the session

        if (loggedCarer != null) {
            // Set the label with the carer's full name and email
            successLabel.setText("Successful Carer Login: "
                    + loggedCarer.getFullName()
                    + " (" + loggedCarer.getEmail() + ")");
        } else {
            successLabel.setText("No Carer Logged In.");
        }
    }
}
