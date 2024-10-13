package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.CarerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCarerProfileDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField passwordField;

    private Carer carer;
    private CarerProfileMenuViewController parentController;

    public void setCarer(Carer carer) {
        this.carer = carer;
        firstNameField.setText(carer.getFirstName());
        lastNameField.setText(carer.getLastName());
        emailField.setText(carer.getEmail());
        phoneField.setText(carer.getPhone());
        addressField.setText(carer.getAddress());
        passwordField.setText(carer.getPassword());
    }

    public void setParentController(CarerProfileMenuViewController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void save() {
        carer.setFirstName(firstNameField.getText());
        carer.setLastName(lastNameField.getText());
        carer.setEmail(emailField.getText());
        carer.setPhone(phoneField.getText());
        carer.setAddress(addressField.getText());
        carer.setPassword(passwordField.getText());

        // Save the updated carer to the database
        CarerDAO carerDAO = new CarerDAO();
        carerDAO.updateCarer(carer);

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