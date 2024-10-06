package com.example.demo.controller;

import com.example.demo.model.MealPlan;
import com.example.demo.model.MealPlanDAO;
import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CreateMealPlanController {

    @FXML private ImageView logoImage;

    // Monday
    @FXML private ComboBox<String> choiceBoxBreakfastMonday;
    @FXML private ComboBox<String> choiceBoxSnackMonday;
    @FXML private ComboBox<String> choiceBoxLunchMonday;
    @FXML private ComboBox<String> choiceBoxSnackMondayEvening;

    // Tuesday
    @FXML private ComboBox<String> choiceBoxBreakfastTuesday;
    @FXML private ComboBox<String> choiceBoxSnackTuesday;
    @FXML private ComboBox<String> choiceBoxLunchTuesday;
    @FXML private ComboBox<String> choiceBoxSnackTuesdayEvening;

    // Wednesday
    @FXML private ComboBox<String> choiceBoxBreakfastWednesday;
    @FXML private ComboBox<String> choiceBoxSnackWednesday;
    @FXML private ComboBox<String> choiceBoxLunchWednesday;
    @FXML private ComboBox<String> choiceBoxSnackWednesdayEvening;

    // Thursday
    @FXML private ComboBox<String> choiceBoxBreakfastThursday;
    @FXML private ComboBox<String> choiceBoxSnackThursday;
    @FXML private ComboBox<String> choiceBoxLunchThursday;
    @FXML private ComboBox<String> choiceBoxSnackThursdayEvening;

    // Friday
    @FXML private ComboBox<String> choiceBoxBreakfastFriday;
    @FXML private ComboBox<String> choiceBoxSnackFriday;
    @FXML private ComboBox<String> choiceBoxLunchFriday;
    @FXML private ComboBox<String> choiceBoxSnackFridayEvening;

    @FXML private DatePicker datePicker;
    @FXML private Button createMealPlan;

    // ImageViews for meals
    @FXML private ImageView imageViewBreakfastMonday;
    @FXML private ImageView imageViewSnackMonday;
    @FXML private ImageView imageViewLunchMonday;
    @FXML private ImageView imageViewSnackMondayEvening;

    @FXML private ImageView imageViewBreakfastTuesday;
    @FXML private ImageView imageViewSnackTuesday;
    @FXML private ImageView imageViewLunchTuesday;
    @FXML private ImageView imageViewSnackTuesdayEvening;

    @FXML private ImageView imageViewBreakfastWednesday;
    @FXML private ImageView imageViewSnackWednesday;
    @FXML private ImageView imageViewLunchWednesday;
    @FXML private ImageView imageViewSnackWednesdayEvening;

    @FXML private ImageView imageViewBreakfastThursday;
    @FXML private ImageView imageViewSnackThursday;
    @FXML private ImageView imageViewLunchThursday;
    @FXML private ImageView imageViewSnackThursdayEvening;

    @FXML private ImageView imageViewBreakfastFriday;
    @FXML private ImageView imageViewSnackFriday;
    @FXML private ImageView imageViewLunchFriday;
    @FXML private ImageView imageViewSnackFridayEvening;

    private final RecipeDAO recipeDAO = new RecipeDAO();
    private Map<String, String> recipeNameToIdMap = new HashMap<>();

    @FXML
    private void initialize() {
        populateAllChoiceBoxes();
        addFieldListeners();
        checkFieldsCompletion();
    }

    private void populateAllChoiceBoxes() {
        populateChoiceBox(choiceBoxBreakfastMonday, "Breakfast", imageViewBreakfastMonday);
        populateChoiceBox(choiceBoxSnackMonday, "Snack", imageViewSnackMonday);
        populateChoiceBox(choiceBoxLunchMonday, "Lunch", imageViewLunchMonday);
        populateChoiceBox(choiceBoxSnackMondayEvening, "Snack", imageViewSnackMondayEvening);

        populateChoiceBox(choiceBoxBreakfastTuesday, "Breakfast", imageViewBreakfastTuesday);
        populateChoiceBox(choiceBoxSnackTuesday, "Snack", imageViewSnackTuesday);
        populateChoiceBox(choiceBoxLunchTuesday, "Lunch", imageViewLunchTuesday);
        populateChoiceBox(choiceBoxSnackTuesdayEvening, "Snack", imageViewSnackTuesdayEvening);

        populateChoiceBox(choiceBoxBreakfastWednesday, "Breakfast", imageViewBreakfastWednesday);
        populateChoiceBox(choiceBoxSnackWednesday, "Snack", imageViewSnackWednesday);
        populateChoiceBox(choiceBoxLunchWednesday, "Lunch", imageViewLunchWednesday);
        populateChoiceBox(choiceBoxSnackWednesdayEvening, "Snack", imageViewSnackWednesdayEvening);

        populateChoiceBox(choiceBoxBreakfastThursday, "Breakfast", imageViewBreakfastThursday);
        populateChoiceBox(choiceBoxSnackThursday, "Snack", imageViewSnackThursday);
        populateChoiceBox(choiceBoxLunchThursday, "Lunch", imageViewLunchThursday);
        populateChoiceBox(choiceBoxSnackThursdayEvening, "Snack", imageViewSnackThursdayEvening);

        populateChoiceBox(choiceBoxBreakfastFriday, "Breakfast", imageViewBreakfastFriday);
        populateChoiceBox(choiceBoxSnackFriday, "Snack", imageViewSnackFriday);
        populateChoiceBox(choiceBoxLunchFriday, "Lunch", imageViewLunchFriday);
        populateChoiceBox(choiceBoxSnackFridayEvening, "Snack", imageViewSnackFridayEvening);
    }

    private void populateChoiceBox(ComboBox<String> choiceBox, String mealType, ImageView imageView) {
        List<Recipe> recipes = recipeDAO.getRecipesByMealType(mealType);
        for (Recipe recipe : recipes) {
            choiceBox.getItems().add(recipe.getRecipeName());
            recipeNameToIdMap.put(recipe.getRecipeName(), recipe.getRecipeId());
        }

        choiceBox.setOnAction(event -> {
            String selectedRecipeName = choiceBox.getValue();
            String recipeId = recipeNameToIdMap.get(selectedRecipeName);
            Recipe selectedRecipe = recipeDAO.getRecipeByName(selectedRecipeName);
            if (selectedRecipe != null && selectedRecipe.getRecipeImage() != null && imageView != null) {
                Image image = new Image(new ByteArrayInputStream(selectedRecipe.getRecipeImage()));
                imageView.setImage(image);
            }
            checkFieldsCompletion();
        });
    }

    private String getRecipeIdFromChoiceBox(ComboBox<String> choiceBox) {
        String selectedRecipeName = choiceBox.getValue();
        return recipeNameToIdMap.get(selectedRecipeName);
    }

    private void addFieldListeners() {
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> checkFieldsCompletion());

        addComboBoxListener(choiceBoxBreakfastMonday);
        addComboBoxListener(choiceBoxSnackMonday);
        addComboBoxListener(choiceBoxLunchMonday);
        addComboBoxListener(choiceBoxSnackMondayEvening);

        addComboBoxListener(choiceBoxBreakfastTuesday);
        addComboBoxListener(choiceBoxSnackTuesday);
        addComboBoxListener(choiceBoxLunchTuesday);
        addComboBoxListener(choiceBoxSnackTuesdayEvening);

        addComboBoxListener(choiceBoxBreakfastWednesday);
        addComboBoxListener(choiceBoxSnackWednesday);
        addComboBoxListener(choiceBoxLunchWednesday);
        addComboBoxListener(choiceBoxSnackWednesdayEvening);

        addComboBoxListener(choiceBoxBreakfastThursday);
        addComboBoxListener(choiceBoxSnackThursday);
        addComboBoxListener(choiceBoxLunchThursday);
        addComboBoxListener(choiceBoxSnackThursdayEvening);

        addComboBoxListener(choiceBoxBreakfastFriday);
        addComboBoxListener(choiceBoxSnackFriday);
        addComboBoxListener(choiceBoxLunchFriday);
        addComboBoxListener(choiceBoxSnackFridayEvening);
    }

    private void addComboBoxListener(ComboBox<String> comboBox) {
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkFieldsCompletion());
    }

    private void checkFieldsCompletion() {
        boolean allFieldsComplete = datePicker.getValue() != null &&
                isComboBoxSelected(choiceBoxBreakfastMonday) &&
                isComboBoxSelected(choiceBoxSnackMonday) &&
                isComboBoxSelected(choiceBoxLunchMonday) &&
                isComboBoxSelected(choiceBoxSnackMondayEvening) &&
                isComboBoxSelected(choiceBoxBreakfastTuesday) &&
                isComboBoxSelected(choiceBoxSnackTuesday) &&
                isComboBoxSelected(choiceBoxLunchTuesday) &&
                isComboBoxSelected(choiceBoxSnackTuesdayEvening) &&
                isComboBoxSelected(choiceBoxBreakfastWednesday) &&
                isComboBoxSelected(choiceBoxSnackWednesday) &&
                isComboBoxSelected(choiceBoxLunchWednesday) &&
                isComboBoxSelected(choiceBoxSnackWednesdayEvening) &&
                isComboBoxSelected(choiceBoxBreakfastThursday) &&
                isComboBoxSelected(choiceBoxSnackThursday) &&
                isComboBoxSelected(choiceBoxLunchThursday) &&
                isComboBoxSelected(choiceBoxSnackThursdayEvening) &&
                isComboBoxSelected(choiceBoxBreakfastFriday) &&
                isComboBoxSelected(choiceBoxSnackFriday) &&
                isComboBoxSelected(choiceBoxLunchFriday) &&
                isComboBoxSelected(choiceBoxSnackFridayEvening);

        createMealPlan.setDisable(!allFieldsComplete);
    }

    private boolean isComboBoxSelected(ComboBox<String> comboBox) {
        return comboBox.getSelectionModel().getSelectedItem() != null;
    }

    @FXML
    private void createMealPlan() {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setMealPlanId(UUID.randomUUID().toString()); // Ensure unique MealPlanId
        mealPlan.setStaffId(""); // Replace with actual staff ID
        mealPlan.setDate(datePicker.getValue().toString());

        // Collect and set RecipeIds using getRecipeIdFromChoiceBox
        mealPlan.setRecipeId("monday", "breakfast", getRecipeIdFromChoiceBox(choiceBoxBreakfastMonday));
        mealPlan.setRecipeId("monday", "snack", getRecipeIdFromChoiceBox(choiceBoxSnackMonday));
        mealPlan.setRecipeId("monday", "lunch", getRecipeIdFromChoiceBox(choiceBoxLunchMonday));
        mealPlan.setRecipeId("monday", "snack2", getRecipeIdFromChoiceBox(choiceBoxSnackMondayEvening));

        mealPlan.setRecipeId("tuesday", "breakfast", getRecipeIdFromChoiceBox(choiceBoxBreakfastTuesday));
        mealPlan.setRecipeId("tuesday", "snack", getRecipeIdFromChoiceBox(choiceBoxSnackTuesday));
        mealPlan.setRecipeId("tuesday", "lunch", getRecipeIdFromChoiceBox(choiceBoxLunchTuesday));
        mealPlan.setRecipeId("tuesday", "snack2", getRecipeIdFromChoiceBox(choiceBoxSnackTuesdayEvening));

        mealPlan.setRecipeId("wednesday", "breakfast", getRecipeIdFromChoiceBox(choiceBoxBreakfastWednesday));
        mealPlan.setRecipeId("wednesday", "snack", getRecipeIdFromChoiceBox(choiceBoxSnackWednesday));
        mealPlan.setRecipeId("wednesday", "lunch", getRecipeIdFromChoiceBox(choiceBoxLunchWednesday));
        mealPlan.setRecipeId("wednesday", "snack2", getRecipeIdFromChoiceBox(choiceBoxSnackWednesdayEvening));

        mealPlan.setRecipeId("thursday", "breakfast", getRecipeIdFromChoiceBox(choiceBoxBreakfastThursday));
        mealPlan.setRecipeId("thursday", "snack", getRecipeIdFromChoiceBox(choiceBoxSnackThursday));
        mealPlan.setRecipeId("thursday", "lunch", getRecipeIdFromChoiceBox(choiceBoxLunchThursday));
        mealPlan.setRecipeId("thursday", "snack2", getRecipeIdFromChoiceBox(choiceBoxSnackThursdayEvening));

        mealPlan.setRecipeId("friday", "breakfast", getRecipeIdFromChoiceBox(choiceBoxBreakfastFriday));
        mealPlan.setRecipeId("friday", "snack", getRecipeIdFromChoiceBox(choiceBoxSnackFriday));
        mealPlan.setRecipeId("friday", "lunch", getRecipeIdFromChoiceBox(choiceBoxLunchFriday));
        mealPlan.setRecipeId("friday", "snack2", getRecipeIdFromChoiceBox(choiceBoxSnackFridayEvening));

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:childcaredb.db")) {
            MealPlanDAO mealPlanDAO = new MealPlanDAO();
            mealPlanDAO.insertMealPlan(mealPlan);

            // Show success alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Meal plan saved successfully!");
            alert.showAndWait();

            // Clear all fields
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();

            // Show error alert
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save meal plan");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void clearFields() {
        datePicker.setValue(null);

        choiceBoxBreakfastMonday.getSelectionModel().clearSelection();
        choiceBoxSnackMonday.getSelectionModel().clearSelection();
        choiceBoxLunchMonday.getSelectionModel().clearSelection();
        choiceBoxSnackMondayEvening.getSelectionModel().clearSelection();

        choiceBoxBreakfastTuesday.getSelectionModel().clearSelection();
        choiceBoxSnackTuesday.getSelectionModel().clearSelection();
        choiceBoxLunchTuesday.getSelectionModel().clearSelection();
        choiceBoxSnackTuesdayEvening.getSelectionModel().clearSelection();

        choiceBoxBreakfastWednesday.getSelectionModel().clearSelection();
        choiceBoxSnackWednesday.getSelectionModel().clearSelection();
        choiceBoxLunchWednesday.getSelectionModel().clearSelection();
        choiceBoxSnackWednesdayEvening.getSelectionModel().clearSelection();

        choiceBoxBreakfastThursday.getSelectionModel().clearSelection();
        choiceBoxSnackThursday.getSelectionModel().clearSelection();
        choiceBoxLunchThursday.getSelectionModel().clearSelection();
        choiceBoxSnackThursdayEvening.getSelectionModel().clearSelection();

        choiceBoxBreakfastFriday.getSelectionModel().clearSelection();
        choiceBoxSnackFriday.getSelectionModel().clearSelection();
        choiceBoxLunchFriday.getSelectionModel().clearSelection();
        choiceBoxSnackFridayEvening.getSelectionModel().clearSelection();

        imageViewBreakfastMonday.setImage(null);
        imageViewSnackMonday.setImage(null);
        imageViewLunchMonday.setImage(null);
        imageViewSnackMondayEvening.setImage(null);

        imageViewBreakfastTuesday.setImage(null);
        imageViewSnackTuesday.setImage(null);
        imageViewLunchTuesday.setImage(null);
        imageViewSnackTuesdayEvening.setImage(null);

        imageViewBreakfastWednesday.setImage(null);
        imageViewSnackWednesday.setImage(null);
        imageViewLunchWednesday.setImage(null);
        imageViewSnackWednesdayEvening.setImage(null);

        imageViewBreakfastThursday.setImage(null);
        imageViewSnackThursday.setImage(null);
        imageViewLunchThursday.setImage(null);
        imageViewSnackThursdayEvening.setImage(null);

        imageViewBreakfastFriday.setImage(null);
        imageViewSnackFriday.setImage(null);
        imageViewLunchFriday.setImage(null);
        imageViewSnackFridayEvening.setImage(null);
    }

    public MealPlan createMealPlan(String selectedRecipe, LocalDate selectedDate) {
        if (selectedRecipe == null) {
            throw new NullPointerException("Recipe cannot be null");
        }
        if (selectedDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }

        MealPlan mealPlan = new MealPlan();
        mealPlan.setMealPlanId(UUID.randomUUID().toString());
        mealPlan.setStaffId(""); // Replace with actual staff ID
        mealPlan.setDate(selectedDate.toString());
        mealPlan.setRecipeId("monday", "breakfast", "1"); // Replace with actual recipe ID based on selectedRecipe

        return mealPlan;
    }

}
