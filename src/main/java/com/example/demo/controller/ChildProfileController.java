package com.example.demo.controller;

import com.example.demo.model.Child;
import com.example.demo.model.ChildDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ChildProfileController {

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
