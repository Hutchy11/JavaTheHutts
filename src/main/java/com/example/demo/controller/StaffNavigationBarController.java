package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Staff Navigation Bar.
 * Handles navigation between different views for staff members.
 */
public class StaffNavigationBarController {

    @FXML
    private void navigateToStaffHome(ActionEvent event) {
        loadView(event, "InitialStaffView.fxml");
    }

    @FXML
    private void navigateToProfile(ActionEvent event) {
        loadView(event, "ProfileView.fxml");
    }

    @FXML
    private void navigateToCarerProfileMenu(ActionEvent event) {
        loadView(event, "CarerProfileMenuView.fxml");
    }

    @FXML
    private void navigateToStaffProfileMenu(ActionEvent event) {
        loadView(event, "StaffProfileMenuView.fxml");
    }

    @FXML
    private void navigateToCreateRecipe(ActionEvent event) {
        loadView(event, "Recipe.fxml");
    }

    @FXML
    private void navigateToViewRecipe(ActionEvent event) {
        loadView(event, "RecipeView.fxml");
    }

    @FXML
    private void navigateToSettings(ActionEvent event) {
        loadView(event, "EditStaffProfile.fxml");
    }

    @FXML
    private void navigateToChildProfile(ActionEvent event) {loadView(event, "StaffChildProfileView.fxml");}

    @FXML
    private void navigateToCreateMealPlan(ActionEvent event) {
        loadView(event, "CreateMealPlanView.fxml");
    }

    @FXML
    private void navigateToViewMealPlan(ActionEvent event) { loadView(event, "MealPlanView.fxml");}


    /**
     * Logs out the current user.
     *
     * @param event the action event triggered by the logout button
     */
    @FXML
    private void logout(ActionEvent event) {
        // Clear session data or perform any necessary cleanup
        System.out.println("Logged out");

        // Redirect to the login view
        loadView(event, "LoginView.fxml");
    }

    /**
     * Loads the specified FXML view.
     *
     * @param event the action event triggered by the navigation button
     * @param fxmlFile the FXML file to load
     */
    private void loadView(ActionEvent event, String fxmlFile) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/" + fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            //showAlert("Error", "Failed to load the view.");
        }
    }

}