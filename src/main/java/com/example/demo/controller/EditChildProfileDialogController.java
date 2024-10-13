// EditChildProfileDialogController.java
package com.example.demo.controller;

import com.example.demo.model.Child;
import com.example.demo.model.ChildDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditChildProfileDialogController {
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

    private Child child;
    private ChildProfileController parentController;

    public void setChild(Child child) {
        this.child = child;
        firstNameField.setText(child.getFirstName());
        lastNameField.setText(child.getLastName());
        dateOfBirthField.setText(child.getDateOfBirth());
        allergiesField.setText(child.getAllergies());
        dietaryField.setText(child.getDietaryRequirements());
        emergencyContactField.setText(child.getEmergencyContact());
    }

    public void setParentController(ChildProfileController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void save() {
        child.setFirstName(firstNameField.getText());
        child.setLastName(lastNameField.getText());
        child.setDateOfBirth(dateOfBirthField.getText());
        child.setAllergies(allergiesField.getText());
        child.setDietaryRequirements(dietaryField.getText());
        child.setEmergencyContact(emergencyContactField.getText());

        // Save the updated child to the database
        ChildDAO childDAO = new ChildDAO();
        childDAO.updateChild(child);

        // Refresh the table in the parent controller
        parentController.refreshTable();

        // Close the dialog
        ((Stage) firstNameField.getScene().getWindow()).close();
    }

    @FXML
    private void cancel() {
        ((Stage) firstNameField.getScene().getWindow()).close();
    }
}