package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.Session;
import com.example.demo.model.Child;
import com.example.demo.model.Staff;
import com.example.demo.model.Session;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;

import java.io.IOException;


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
    private void navigateChildProfile(ActionEvent event) {
        try {
            // Get the current stage (window) and load the new FXML
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/StaffChildProfileView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    private void createPost(ActionEvent event) {
        // Implement the navigation logic here
        System.out.println("Creating Post");
    }
}