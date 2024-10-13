package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.CarerDAO;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for editing a carer's profile.
 */
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

    /**
     * Sets the carer to be edited.
     *
     * @param carer the carer to be edited
     */
    public void setCarer(Carer carer) {
        this.carer = carer;
        firstNameField.setText(carer.getFirstName());
        lastNameField.setText(carer.getLastName());
        emailField.setText(carer.getEmail());
        phoneField.setText(carer.getPhone());
        addressField.setText(carer.getAddress());
        passwordField.setText(carer.getPassword());
    }

    /**
     * Sets the parent controller.
     *
     * @param parentController the parent controller
     */
    public void setParentController(CarerProfileMenuViewController parentController) {
        this.parentController = parentController;
    }

    /**
     * Saves the edited carer profile.
     */
    @FXML
    private void save() {
        carer.setFirstName(firstNameField.getText());
        carer.setLastName(lastNameField.getText());
        carer.setEmail(emailField.getText());
        carer.setPhone(phoneField.getText());
        carer.setAddress(addressField.getText());
        carer.setPassword(passwordField.getText());

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                CarerDAO carerDAO = new CarerDAO();
                carerDAO.updateCarer(carer);
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