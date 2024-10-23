package com.example.demo.controller;
import com.example.demo.model.Session;
import com.example.demo.model.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * Controller class for the Initial Staff View.
 * Handles the display and interaction with the staff's initial view.
 */
public class InitialStaffViewController {

    @FXML
    private Label successLabel;

    /**
     * Initializes the controller class.
     * Sets up the success label with the logged-in staff's information.
     */
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

    /**
     * Handles the action of creating a post.
     * This method is triggered by the corresponding UI event.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    private void createPost(ActionEvent event) {
        // Implement the navigation logic here
        System.out.println("Creating Post");
    }
}