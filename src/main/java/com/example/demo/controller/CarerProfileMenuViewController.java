package com.example.demo.controller;

import com.example.demo.model.Carer;
import com.example.demo.model.CarerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Carer Profile Menu View.
 * Manages the display and interaction with the carer profile data.
 */
public class CarerProfileMenuViewController {

    @FXML
    private TableView<Carer> carerTableView;
    @FXML
    private TableColumn<Carer, String> carerIdColumn;
    @FXML
    private TableColumn<Carer, String> firstNameColumn;
    @FXML
    private TableColumn<Carer, String> lastNameColumn;
    @FXML
    private TableColumn<Carer, String> emailColumn;
    @FXML
    private TableColumn<Carer, String> phoneColumn;
    @FXML
    private TableColumn<Carer, String> addressColumn;

    private CarerDAO carerDAO;

    /**
     * Constructor for CarerProfileMenuViewController.
     * Initializes the CarerDAO.
     */
    public CarerProfileMenuViewController() {
        carerDAO = new CarerDAO();
    }

    /**
     * Initializes the controller class.
     * Sets up the table columns and loads carer data.
     */
    @FXML
    public void initialize() {
        carerIdColumn.setCellValueFactory(new PropertyValueFactory<>("carerId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadCarerData();
    }

    /**
     * Loads carer data from the database and sets it to the table view.
     */
    private void loadCarerData() {
        ObservableList<Carer> carerList = FXCollections.observableArrayList(carerDAO.getAllCarers());
        carerTableView.setItems(carerList);
    }

    /**
     * Navigates to the Register Carer view.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    private void navigateToRegisterCarer(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/RegisterCarerView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}