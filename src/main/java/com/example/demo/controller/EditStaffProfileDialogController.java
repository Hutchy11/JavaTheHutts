package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.model.StaffDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditStaffProfileDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField roleField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField hireDateField; // Ensure this matches the fx:id in the FXML

    private Staff staff;
    private StaffProfileMenuViewController parentController;

    public void setStaff(Staff staff) {
        this.staff = staff;
        firstNameField.setText(staff.getFirstName());
        lastNameField.setText(staff.getLastName());
        emailField.setText(staff.getEmail());
        phoneField.setText(staff.getPhone());
        roleField.setText(staff.getRole());
        passwordField.setText(staff.getPassword());
        hireDateField.setText(staff.getHireDate());
    }

    public void setParentController(StaffProfileMenuViewController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void save() {
        staff.setFirstName(firstNameField.getText());
        staff.setLastName(lastNameField.getText());
        staff.setEmail(emailField.getText());
        staff.setPhone(phoneField.getText());
        staff.setRole(roleField.getText());
        staff.setPassword(passwordField.getText());
        staff.setHireDate(hireDateField.getText());

        StaffDAO staffDAO = new StaffDAO();
        staffDAO.updateStaff(staff);

        parentController.refreshTable();

        ((Stage) firstNameField.getScene().getWindow()).close();
    }

    @FXML
    private void cancel() {
        ((Stage) firstNameField.getScene().getWindow()).close();
    }
}