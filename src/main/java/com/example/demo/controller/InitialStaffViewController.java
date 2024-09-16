package com.example.demo.controller;

import com.example.demo.model.Child;
import com.example.demo.model.Staff;
import com.example.demo.model.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;

import java.io.IOException;

public class InitialStaffViewController {


    @FXML
    private Label successLabel;
    @FXML
    private VBox centerVBox;
    @FXML
    private TableView<Child> childTableView;
    @FXML
    private TableColumn<Child, String> idColumn;
    @FXML
    private TableColumn<Child, String> parentID;
    @FXML
    private TableColumn<Child, String> nameColumn;
    @FXML
    private TableColumn<Child, String> dobColumn;
    @FXML
    private TableColumn<Child, String> allergiesColumn;
    @FXML
    private TableColumn<Child, String> dietaryColumn;
    @FXML
    private TableColumn<Child, String> emergencyContactColumn;

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

    }
}
