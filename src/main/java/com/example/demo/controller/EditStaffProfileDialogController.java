package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.model.StaffDAO;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for editing a staff member's profile.
 */
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
    private TextField hireDateField;

    private Staff staff;
    private StaffProfileMenuViewController parentController;

    /**
     * Sets the staff member to be edited.
     *
     * @param staff the staff member to be edited
     */
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

    /**
     * Sets the parent controller.
     *
     * @param parentController the parent controller
     */
    public void setParentController(StaffProfileMenuViewController parentController) {
        this.parentController = parentController;
    }

    /**
     * Saves the edited staff profile.
     */
    @FXML
    private void save() {
        staff.setFirstName(firstNameField.getText());
        staff.setLastName(lastNameField.getText());
        staff.setEmail(emailField.getText());
        staff.setPhone(phoneField.getText());
        staff.setRole(roleField.getText());
        staff.setPassword(passwordField.getText());
        staff.setHireDate(hireDateField.getText());

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                StaffDAO staffDAO = new StaffDAO();
                staffDAO.updateStaff(staff);
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