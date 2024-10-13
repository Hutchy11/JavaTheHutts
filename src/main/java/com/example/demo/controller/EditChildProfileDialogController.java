package com.example.demo.controller;

import com.example.demo.model.Child;
import com.example.demo.model.ChildDAO;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for editing a child's profile.
 */
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


    /**
     * Sets the child to be edited.
     *
     * @param child the child to be edited
     */
    public void setChild(Child child) {
        this.child = child;
        firstNameField.setText(child.getFirstName());
        lastNameField.setText(child.getLastName());
        dateOfBirthField.setText(child.getDateOfBirth());
        allergiesField.setText(child.getAllergies());
        dietaryField.setText(child.getDietaryRequirements());
        emergencyContactField.setText(child.getEmergencyContact());
    }

    /**
     * Sets the parent controller.
     *
     * @param parentController the parent controller
     */
    public void setParentController(ChildProfileController parentController) {
        this.parentController = parentController;
    }

    /**
     * Saves the edited child profile.
     */
    @FXML
    private void save() {
        child.setFirstName(firstNameField.getText());
        child.setLastName(lastNameField.getText());
        child.setDateOfBirth(dateOfBirthField.getText());
        child.setAllergies(allergiesField.getText());
        child.setDietaryRequirements(dietaryField.getText());
        child.setEmergencyContact(emergencyContactField.getText());

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                ChildDAO childDAO = new ChildDAO();
                childDAO.updateChild(child);
                return null;
            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    parentController.refreshTable();
                    ((Stage) firstNameField.getScene().getWindow()).close();
                });
            }

            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    // Handle failure (e.g., show an error message)
                });
            }
        };

        new Thread(task).start();
    }

    /**
     * Cancels the edit operation and closes the dialog.
     */
    @FXML
    private void cancel() {
        ((Stage) firstNameField.getScene().getWindow()).close();
    }
}