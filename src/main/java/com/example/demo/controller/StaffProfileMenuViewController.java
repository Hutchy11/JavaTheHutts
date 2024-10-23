package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.model.StaffDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for the Staff Profile Menu View.
 * Handles the display and interaction with staff profiles.
 */
public class StaffProfileMenuViewController {

    @FXML
    private TableView<Staff> staffTableView;
    @FXML
    private TableColumn<Staff, String> staffIdColumn;
    @FXML
    private TableColumn<Staff, String> firstNameColumn;
    @FXML
    private TableColumn<Staff, String> lastNameColumn;
    @FXML
    private TableColumn<Staff, String> emailColumn;
    @FXML
    private TableColumn<Staff, String> phoneColumn;
    @FXML
    private TableColumn<Staff, String> roleColumn;
    @FXML
    private TableColumn<Staff, String> hireDateColumn;

    private StaffDAO staffDAO;

    /**
     * Constructor for the StaffProfileMenuViewController.
     * Initializes the StaffDAO.
     */
    public StaffProfileMenuViewController() {
        staffDAO = new StaffDAO();
    }

    /**
     * Initializes the controller class.
     * Sets up the table columns and loads the staff data.
     */
    @FXML
    public void initialize() {
        staffIdColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        staffTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && staffTableView.getSelectionModel().getSelectedItem() != null) {
                openEditStaffProfileDialog(staffTableView.getSelectionModel().getSelectedItem());
            }
        });

        loadStaffData();
    }

    /**
     * Loads the staff data into the table view.
     */
    private void loadStaffData() {
        ObservableList<Staff> staffList = FXCollections.observableArrayList(staffDAO.getAllStaffs());
        staffTableView.setItems(staffList);
    }

    /**
     * Navigates to the Register Staff view.
     *
     * @param event the action event triggered by the navigation button
     */
    @FXML
    private void navigateToRegisterStaff(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/RegisterStaffView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openEditStaffProfileDialog(Staff staff) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/EditStaffProfile.fxml"));
            Parent parent = loader.load();

            EditStaffProfileDialogController controller = loader.getController();
            controller.setStaff(staff);
            controller.setParentController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        List<Staff> staff = staffDAO.getAllStaffs();
        ObservableList<Staff> staffList = FXCollections.observableArrayList(staff);
        staffTableView.setItems(staffList);
    }

}
