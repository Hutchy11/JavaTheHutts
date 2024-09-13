package com.example.demo.controller;

import com.example.demo.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class ChildProfileFormController {

    private CarerDAO carerDAO = new CarerDAO(); // Initialize CarerDAO
    private ChildDAO childDAO = new ChildDAO(); // Initialize ChildDAO

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField dateOfBirthField;
    @FXML
    private TextField allergiesField;
    @FXML
    private TextField dietaryField;
    @FXML
    private TextField emergencyContactField;
    @FXML
    private ComboBox<Carer> carerComboBox;

    public void initialize() {
        // Populate the carer dropdown with data from the database
        List<Carer> carers = carerDAO.getAllCarers();
        carerComboBox.setItems(FXCollections.observableArrayList(carers));
    }

    @FXML
    public void submitChildProfile() {
        // Generate a new UUID for the childId
        String childId = UUID.randomUUID().toString();
        // Gather the input data
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String dateOfBirth = dateOfBirthField.getText();
        String allergies = allergiesField.getText();
        String dietaryRequirements = dietaryField.getText();
        String emergencyContact = emergencyContactField.getText();
        Carer selectedCarer = carerComboBox.getValue();  // Selected Carer from the dropdown

        // Create a new Child object
        Child newChild = new Child(childId, selectedCarer.getCarerId(), firstName, lastName, dateOfBirth, allergies, dietaryRequirements, emergencyContact);

        // Save the child data to the database
        try {
            childDAO.insertChild(newChild);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close the form window
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
