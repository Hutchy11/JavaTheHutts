package com.example.demo.controller;

import com.example.demo.model.Child;
import com.example.demo.model.ChildDAO;
import com.example.demo.model.Staff;
import com.example.demo.model.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class InitialStaffViewController {


    @FXML
    private Label successLabel;
    @FXML
    private VBox centerVBox;
    @FXML
    private TableView<Child> childTableView;
    @FXML
    private TableColumn<Child, String> idColumn;
    @FXML
    private TableColumn<Child, String> parentID;
    @FXML
    private TableColumn<Child, String> nameColumn;
    @FXML
    private TableColumn<Child, String> dobColumn;
    @FXML
    private TableColumn<Child, String> allergiesColumn;
    @FXML
    private TableColumn<Child, String> dietaryColumn;
    @FXML
    private TableColumn<Child, String> emergencyContactColumn;



    public void initialize() {
        Staff loggedStaff = Session.getLoggedStaff();  // Retrieve the Staff from the session

        if (loggedStaff != null) {
            // Set the label with the Staff's full name and email
            successLabel.setText("Successful Staff Login: "
                    + loggedStaff.getFullName()
                    + " (" + loggedStaff.getEmail() + ")");
        } else {
            successLabel.setText("No Staff Logged In.");
        }
    }


    @FXML
    private void createChildProfile() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/ChildProfileForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create Child Profile");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void viewChildProfiles() {
        ChildDAO childDAO = new ChildDAO();
        List<Child> children = childDAO.getAllChildren();
        ObservableList<Child> childList = FXCollections.observableArrayList(children);

        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("childId"));
        parentID.setCellValueFactory(new PropertyValueFactory<>("parentId"));
        nameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFirstName() + " " + cellData.getValue().getLastName()));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        allergiesColumn.setCellValueFactory(new PropertyValueFactory<>("allergies"));
        dietaryColumn.setCellValueFactory(new PropertyValueFactory<>("dietaryRequirements"));
        emergencyContactColumn.setCellValueFactory(new PropertyValueFactory<>("emergencyContact"));

        // Set items in table
        childTableView.setItems(childList);
        // Show the table view
        childTableView.setVisible(true);

    }
}
