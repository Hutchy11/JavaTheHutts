package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.CarerDAO;
import com.example.demo.model.Child;
import com.example.demo.model.ChildDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.UUID;

public class ChildProfileFormController {

    private final CarerDAO carerDAO = new CarerDAO(); // Initialize CarerDAO
    private final ChildDAO childDAO = new ChildDAO(); // Initialize ChildDAO

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

    public void initialize() {
        // Populate the carer dropdown with data from the database
        List<Carer> carers = carerDAO.getAllCarers();
        carerComboBox.setItems(FXCollections.observableArrayList(carers));

        // Add listeners to enable/disable the submit button
        addValidationListeners();
        validateFields();
    }

    private void addValidationListeners() {
        firstNameField.textProperty().addListener((obs, oldText, newText) -> validateFields());
        lastNameField.textProperty().addListener((obs, oldText, newText) -> validateFields());
        dateOfBirthField.textProperty().addListener((obs, oldText, newText) -> validateFields());
        emergencyContactField.textProperty().addListener((obs, oldText, newText) -> validateFields());
        carerComboBox.valueProperty().addListener((obs, oldCarer, newCarer) -> validateFields());
    }

    private void validateFields() {
        boolean isFormValid = !firstNameField.getText().isEmpty() &&
                !lastNameField.getText().isEmpty() &&
                !dateOfBirthField.getText().isEmpty() &&
                !emergencyContactField.getText().isEmpty() &&
                carerComboBox.getValue() != null; // Check if a carer is selected

        submitButton.setDisable(!isFormValid);
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
        childDAO.insertChild(newChild);
        // Show success alert
        showAlert(Alert.AlertType.INFORMATION, "Success", "Child profile successfully created!");

        // Close the form window
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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
