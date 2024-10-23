package com.example.demo.controller;

import com.example.demo.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.UUID;

import javafx.scene.control.Button;


/**
 * Controller class for the Child Profile Form.
 * Handles the creation and submission of new child profiles.
 */
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
    @FXML
    private Button submitButton;

    /**
     * Initializes the controller class.
     * Populates the carer dropdown with data from the database and adds validation listeners.
     */
    public void initialize() {
        // Populate the carer dropdown with data from the database
        List<Carer> carers = carerDAO.getAllCarers();
        carerComboBox.setItems(FXCollections.observableArrayList(carers));

        // Add listeners to enable/disable the submit button
        addValidationListeners();
        validateFields();
    }


    /**
     * Adds validation listeners to the form fields.
     * Enables or disables the submit button based on the form's validity.
     */
    private void addValidationListeners() {
        firstNameField.textProperty().addListener((obs, oldText, newText) -> validateFields());
        lastNameField.textProperty().addListener((obs, oldText, newText) -> validateFields());
        dateOfBirthField.textProperty().addListener((obs, oldText, newText) -> validateFields());
        emergencyContactField.textProperty().addListener((obs, oldText, newText) -> validateFields());
        carerComboBox.valueProperty().addListener((obs, oldCarer, newCarer) -> validateFields());
    }

    /**
     * Validates the form fields.
     * Enables the submit button if all required fields are filled.
     */
    private void validateFields() {
        boolean isFormValid = !firstNameField.getText().isEmpty() &&
                !lastNameField.getText().isEmpty() &&
                !dateOfBirthField.getText().isEmpty() &&
                !emergencyContactField.getText().isEmpty() &&
                carerComboBox.getValue() != null; // Check if a carer is selected

        submitButton.setDisable(!isFormValid);
    }


    /**
     * Submits the child profile form.
     * Gathers input data, creates a new Child object, and saves it to the database.
     */
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
        childDAO.insertChild(newChild);
        // Show success alert
        showAlert(Alert.AlertType.INFORMATION, "Success", "Child profile successfully created!");

        // Close the form window
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Shows an alert with the specified type, title, and message.
     *
     * @param alertType the type of alert
     * @param title the title of the alert
     * @param message the message of the alert
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Clears all form fields.
     */
    @FXML
    public void clearForm() {
        // Clear all text fields
        firstNameField.clear();
        lastNameField.clear();
        dateOfBirthField.clear();
        allergiesField.clear();
        dietaryField.clear();
        emergencyContactField.clear();
    }

}
