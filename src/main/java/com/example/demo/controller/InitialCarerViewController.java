package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.Child;
import com.example.demo.model.ChildDAO;
import com.example.demo.model.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for the Initial Carer View.
 * Handles the display and interaction with the carer's initial view.
 */
public class InitialCarerViewController {

    @FXML
    private Label successLabel;
    @FXML
    private TableView<Child> childTableView;
    @FXML
    private TableColumn<Child, String> idColumn;
    @FXML
    private TableColumn<Child, String> parentIDColumn;
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
     * Initializes the controller class.
     * Sets up the success label with the logged-in carer's information.
     */
    public void initialize() {
        Carer loggedCarer = Session.getLoggedCarer();  // Retrieve the carer from the session

        if (loggedCarer != null) {
            // Set the label with the carer's full name and email
            successLabel.setText("Successful Carer Login: "
                    + loggedCarer.getFullName()
                    + " (" + loggedCarer.getEmail() + ")");
        } else {
            successLabel.setText("No Carer Logged In.");
        }
    }

    /**
     * Displays the children profiles related to the logged-in carer in the table view.
     */
    @FXML
    private void viewMyChildren() {
        Carer loggedCarer = Session.getLoggedCarer();
        if (loggedCarer == null) {
            successLabel.setText("No Carer Logged In.");
            return;
        }

        ChildDAO childDAO = new ChildDAO();
        List<Child> allChildren = childDAO.getAllChildren();
        // Filter children to include only those belonging to the logged-in carer
        List<Child> myChildren = allChildren.stream()
                .filter(child -> child.getParentId().equals(loggedCarer.getCarerId()))
                .collect(Collectors.toList());

        ObservableList<Child> childList = FXCollections.observableArrayList(myChildren);

        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("childId"));
        parentIDColumn.setCellValueFactory(new PropertyValueFactory<>("parentId"));
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
