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

    public StaffProfileMenuViewController() {
        staffDAO = new StaffDAO();
    }

    @FXML
    public void initialize() {
        staffIdColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        loadStaffData();
    }

    private void loadStaffData() {
        ObservableList<Staff> staffList = FXCollections.observableArrayList(staffDAO.getAllStaffs());
        staffTableView.setItems(staffList);
    }

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

    public static class CreateMealPlanController {

        @FXML
        private ImageView logoImage;

        @FXML
        private HBox headerHbox;

        @FXML
        private VBox mainContent;

        @FXML
        private Label successLabel;

        @FXML
        private DatePicker datePicker;

        @FXML
        private GridPane mealPlanGrid;

        @FXML
        private ChoiceBox<String> choiceBox1;

        @FXML
        private ChoiceBox<String> choiceBox2;

        @FXML
        private ChoiceBox<String> choiceBox3;

        @FXML
        private ChoiceBox<String> choiceBox4;

        @FXML
        private ChoiceBox<String> choiceBox5;

        @FXML
        private Button saveButton;

        @FXML
        private Button cancelButton;

        @FXML
        private void initialize() {
            // Initialize any required data or setup here
        }

        @FXML
        private void handleSaveMealPlan() {
            // Handle the save meal plan action
            System.out.println("Meal plan saved!");
        }

        @FXML
        private void handleCancelMealPlan() {
            // Handle the cancel action
            System.out.println("Meal plan creation canceled!");
        }
    }
}
