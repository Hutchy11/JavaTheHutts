package com.example.demo.controller;

import com.example.demo.model.MealPlan;
import com.example.demo.model.MealPlanDAO;
import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CreateMealPlanController {

    @FXML
    private ImageView logoImage;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<Recipe> mondayBreakfastChoiceBox;
    @FXML
    private ChoiceBox<Recipe> mondaySnackChoiceBox;
    @FXML
    private ChoiceBox<Recipe> mondayLunchChoiceBox;

    @FXML
    private ChoiceBox<Recipe> tuesdayBreakfastChoiceBox;
    @FXML
    private ChoiceBox<Recipe> tuesdaySnackChoiceBox;
    @FXML
    private ChoiceBox<Recipe> tuesdayLunchChoiceBox;

    @FXML
    private ChoiceBox<Recipe> wednesdayBreakfastChoiceBox;
    @FXML
    private ChoiceBox<Recipe> wednesdaySnackChoiceBox;
    @FXML
    private ChoiceBox<Recipe> wednesdayLunchChoiceBox;

    @FXML
    private ChoiceBox<Recipe> thursdayBreakfastChoiceBox;
    @FXML
    private ChoiceBox<Recipe> thursdaySnackChoiceBox;
    @FXML
    private ChoiceBox<Recipe> thursdayLunchChoiceBox;

    @FXML
    private ChoiceBox<Recipe> fridayBreakfastChoiceBox;
    @FXML
    private ChoiceBox<Recipe> fridaySnackChoiceBox;
    @FXML
    private ChoiceBox<Recipe> fridayLunchChoiceBox;

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private RecipeDAO recipeDAO = new RecipeDAO();
    private MealPlanDAO mealPlanDAO;

    @FXML
    private void initialize() {
        // Initialize the controller and populate choice boxes
        logoImage.setImage(new Image(getClass().getResourceAsStream("/styles/logo.png")));
        populateChoiceBoxes();
        addChoiceBoxListeners();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:your-database-file.db");
            mealPlanDAO = new MealPlanDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateChoiceBoxes() {
        List<Recipe> recipes = recipeDAO.getAllRecipes();
        mondayBreakfastChoiceBox.getItems().addAll(recipes);
        mondaySnackChoiceBox.getItems().addAll(recipes);
        mondayLunchChoiceBox.getItems().addAll(recipes);
        tuesdayBreakfastChoiceBox.getItems().addAll(recipes);
        tuesdaySnackChoiceBox.getItems().addAll(recipes);
        tuesdayLunchChoiceBox.getItems().addAll(recipes);
        wednesdayBreakfastChoiceBox.getItems().addAll(recipes);
        wednesdaySnackChoiceBox.getItems().addAll(recipes);
        wednesdayLunchChoiceBox.getItems().addAll(recipes);
        thursdayBreakfastChoiceBox.getItems().addAll(recipes);
        thursdaySnackChoiceBox.getItems().addAll(recipes);
        thursdayLunchChoiceBox.getItems().addAll(recipes);
        fridayBreakfastChoiceBox.getItems().addAll(recipes);
        fridaySnackChoiceBox.getItems().addAll(recipes);
        fridayLunchChoiceBox.getItems().addAll(recipes);
    }

    private void addChoiceBoxListeners() {
        mondayBreakfastChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        mondaySnackChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        mondayLunchChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        tuesdayBreakfastChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        tuesdaySnackChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        tuesdayLunchChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        wednesdayBreakfastChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        wednesdaySnackChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        wednesdayLunchChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        thursdayBreakfastChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        thursdaySnackChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        thursdayLunchChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        fridayBreakfastChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        fridaySnackChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
        fridayLunchChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateImageView(newVal));
    }

    private void updateImageView(Recipe recipe) {
        if (recipe != null && recipe.getRecipeImage() != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(recipe.getRecipeImage());
            Image image = new Image(bis);
            logoImage.setImage(image);
        }
    }

    @FXML
    private void handleSaveMealPlan() {
        // Handle the save button action
        MealPlan mealPlan = new MealPlan();
        mealPlan.setMealPlanId(UUID.randomUUID().toString());
        mealPlan.setStaffId("some-staff-id"); // Replace with actual staff ID
        mealPlan.setDate(datePicker.getValue().toString());

        // Set recipes for each day
        mealPlan.setMondayBreakfastRecipeId(getRecipeId(mondayBreakfastChoiceBox));
        mealPlan.setMondaySnackRecipeId(getRecipeId(mondaySnackChoiceBox));
        mealPlan.setMondayLunchRecipeId(getRecipeId(mondayLunchChoiceBox));

        mealPlan.setTuesdayBreakfastRecipeId(getRecipeId(tuesdayBreakfastChoiceBox));
        mealPlan.setTuesdaySnackRecipeId(getRecipeId(tuesdaySnackChoiceBox));
        mealPlan.setTuesdayLunchRecipeId(getRecipeId(tuesdayLunchChoiceBox));

        mealPlan.setWednesdayBreakfastRecipeId(getRecipeId(wednesdayBreakfastChoiceBox));
        mealPlan.setWednesdaySnackRecipeId(getRecipeId(wednesdaySnackChoiceBox));
        mealPlan.setWednesdayLunchRecipeId(getRecipeId(wednesdayLunchChoiceBox));

        mealPlan.setThursdayBreakfastRecipeId(getRecipeId(thursdayBreakfastChoiceBox));
        mealPlan.setThursdaySnackRecipeId(getRecipeId(thursdaySnackChoiceBox));
        mealPlan.setThursdayLunchRecipeId(getRecipeId(thursdayLunchChoiceBox));

        mealPlan.setFridayBreakfastRecipeId(getRecipeId(fridayBreakfastChoiceBox));
        mealPlan.setFridaySnackRecipeId(getRecipeId(fridaySnackChoiceBox));
        mealPlan.setFridayLunchRecipeId(getRecipeId(fridayLunchChoiceBox));

        mealPlan.setNotes("Some notes"); // Replace with actual notes

        try {
            mealPlanDAO.saveMealPlan(mealPlan);
            System.out.println("Meal plan saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancelMealPlan() {
        // Handle the cancel button action
        System.out.println("Cancel button clicked");
    }

    private String getRecipeId(ChoiceBox<Recipe> choiceBox) {
        Recipe selectedRecipe = choiceBox.getValue();
        return selectedRecipe != null ? selectedRecipe.getRecipeId() : null;
    }

    // Getters and setters for the fields if needed
    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public ChoiceBox<Recipe> getMondayBreakfastChoiceBox() {
        return mondayBreakfastChoiceBox;
    }

    public void setMondayBreakfastChoiceBox(ChoiceBox<Recipe> mondayBreakfastChoiceBox) {
        this.mondayBreakfastChoiceBox = mondayBreakfastChoiceBox;
    }

    // Add similar getters and setters for other fields as needed
}
