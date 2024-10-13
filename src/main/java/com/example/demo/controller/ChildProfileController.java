package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.Child;
import com.example.demo.model.ChildDAO;
import com.example.demo.model.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for the Child Profile View.
 * Manages the display and interaction with the child profile data.
 */
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

    /**
     * Opens the form to create a new child profile.
     */
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

    /**
     * Displays all child profiles in the table view.
     */
    @FXML
    private void viewAllChildProfiles() {
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

        // Add row click event
        childTableView.setRowFactory(tv -> {
            TableRow<Child> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Child rowData = row.getItem();
                    openEditChildProfileDialog(rowData);
                }
            });
            return row;
        });

        refreshTable();

    }

    /**
     * Displays the child profiles related to the logged-in carer in the table view.
     */
    @FXML
    private void viewMyChildProfiles() {
        // Get the logged-in carer's ID from the session
        Carer loggedCarer = Session.getLoggedCarer();

        if (loggedCarer != null) {
            String parentId = loggedCarer.getCarerId();

            // Fetch only children related to the logged-in parent
            ChildDAO childDAO = new ChildDAO();
            List<Child> children = childDAO.getChildrenByParent(parentId);  // Use the method from previous query
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
        } else {
            // Handle the case where no parent is logged in (optional)
            System.out.println("No carer is logged in.");
        }
    }

    public void openEditChildProfileDialog(Child child) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/EditChildProfile.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Edit Child Profile");
            stage.setScene(new Scene(fxmlLoader.load()));
            EditChildProfileDialogController controller = fxmlLoader.getController();
            controller.setChild(child);
            controller.setParentController(this); // Set the parent controller
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        // Re-fetch the data and refresh the table view
        ChildDAO childDAO = new ChildDAO();
        List<Child> children = childDAO.getAllChildren();
        ObservableList<Child> childList = FXCollections.observableArrayList(children);
        childTableView.setItems(childList);
    }

}
