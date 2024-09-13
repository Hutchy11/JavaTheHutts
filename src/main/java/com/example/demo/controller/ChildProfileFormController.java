package com.example.demo.controller;

import com.example.demo.model.Child;
import com.example.demo.model.Carer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.UUID;

public class ChildProfileFormController {

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
        // Populate the parent dropdown with data from the database
        carerComboBox.setItems(FXCollections.observableArrayList(CarerRepository.getAllCarers()));
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
        Child newChild = new Child(childId, firstName, lastName, dateOfBirth, allergies, dietaryRequirements, emergencyContact, selectedCarer.getCarerId());

        // Save the child data to the database
        // Example: ChildRepository.addChild(newChild);

        // Close the form window
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
