package com.example.demo.controller;

import com.example.demo.model.MealPlan;
import com.example.demo.model.MealPlanDAO;
import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateMealPlanController {

    private final RecipeDAO recipeDAO = new RecipeDAO();
    @FXML
    private ImageView logoImage;
    // Monday
    @FXML
    private ComboBox<String> choiceBoxBreakfastMonday;
    @FXML
    private ComboBox<String> choiceBoxSnackMonday;
    @FXML
    private ComboBox<String> choiceBoxLunchMonday;
    @FXML
    private ComboBox<String> choiceBoxSnackMondayEvening;
    // Tuesday
    @FXML
    private ComboBox<String> choiceBoxBreakfastTuesday;
    @FXML
    private ComboBox<String> choiceBoxSnackTuesday;
    @FXML
    private ComboBox<String> choiceBoxLunchTuesday;
    @FXML
    private ComboBox<String> choiceBoxSnackTuesdayEvening;
    // Wednesday
    @FXML
    private ComboBox<String> choiceBoxBreakfastWednesday;
    @FXML
    private ComboBox<String> choiceBoxSnackWednesday;
    @FXML
    private ComboBox<String> choiceBoxLunchWednesday;
    @FXML
    private ComboBox<String> choiceBoxSnackWednesdayEvening;
    // Thursday
    @FXML
    private ComboBox<String> choiceBoxBreakfastThursday;
    @FXML
    private ComboBox<String> choiceBoxSnackThursday;
    @FXML
    private ComboBox<String> choiceBoxLunchThursday;
    @FXML
    private ComboBox<String> choiceBoxSnackThursdayEvening;
    // Friday
    @FXML
    private ComboBox<String> choiceBoxBreakfastFriday;
    @FXML
    private ComboBox<String> choiceBoxSnackFriday;
    @FXML
    private ComboBox<String> choiceBoxLunchFriday;
    @FXML
    private ComboBox<String> choiceBoxSnackFridayEvening;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button createMealPlan;
    // ImageViews for meals
    @FXML
    private ImageView imageViewBreakfastMonday;
    @FXML
    private ImageView imageViewSnackMonday;
    @FXML
    private ImageView imageViewLunchMonday;
    @FXML
    private ImageView imageViewSnackMondayEvening;
    @FXML
    private ImageView imageViewBreakfastTuesday;
    @FXML
    private ImageView imageViewSnackTuesday;
    @FXML
    private ImageView imageViewLunchTuesday;
    @FXML
    private ImageView imageViewSnackTuesdayEvening;
    @FXML
    private ImageView imageViewBreakfastWednesday;
    @FXML
    private ImageView imageViewSnackWednesday;
    @FXML
    private ImageView imageViewLunchWednesday;
    @FXML
    private ImageView imageViewSnackWednesdayEvening;
    @FXML
    private ImageView imageViewBreakfastThursday;
    @FXML
    private ImageView imageViewSnackThursday;
    @FXML
    private ImageView imageViewLunchThursday;
    @FXML
    private ImageView imageViewSnackThursdayEvening;
    @FXML
    private ImageView imageViewBreakfastFriday;
    @FXML
    private ImageView imageViewSnackFriday;
    @FXML
    private ImageView imageViewLunchFriday;
    @FXML
    private ImageView imageViewSnackFridayEvening;

    @FXML
    private void initialize() {
        populateAllChoiceBoxes();
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
        }

        choiceBox.setOnAction(event -> {
            String selectedRecipeName = choiceBox.getSelectionModel().getSelectedItem();
            Recipe selectedRecipe = recipeDAO.getRecipeByName(selectedRecipeName);
            if (selectedRecipe != null && selectedRecipe.getRecipeImage() != null && imageView != null) {
                Image image = new Image(new ByteArrayInputStream(selectedRecipe.getRecipeImage()));
                imageView.setImage(image);
            }
        });
    }

    @FXML
    private void createMealPlan() {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setMealPlanId(UUID.randomUUID().toString());
        mealPlan.setStaffId(""); // Replace with actual staff ID
        mealPlan.setDate(datePicker.getValue().toString());

        // Set recipe IDs for each day
        setMealPlanForDay(mealPlan, "monday", choiceBoxBreakfastMonday, choiceBoxSnackMonday, choiceBoxSnackMondayEvening, choiceBoxLunchMonday);
        setMealPlanForDay(mealPlan, "tuesday", choiceBoxBreakfastTuesday, choiceBoxSnackTuesday, choiceBoxSnackTuesdayEvening, choiceBoxLunchTuesday);
        setMealPlanForDay(mealPlan, "wednesday", choiceBoxBreakfastWednesday, choiceBoxSnackWednesday, choiceBoxSnackWednesdayEvening, choiceBoxLunchWednesday);
        setMealPlanForDay(mealPlan, "thursday", choiceBoxBreakfastThursday, choiceBoxSnackThursday, choiceBoxSnackThursdayEvening, choiceBoxLunchThursday);
        setMealPlanForDay(mealPlan, "friday", choiceBoxBreakfastFriday, choiceBoxSnackFriday, choiceBoxSnackFridayEvening, choiceBoxLunchFriday);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:childcaredb.db")) {
            MealPlanDAO mealPlanDAO = new MealPlanDAO(connection);
            mealPlanDAO.saveMealPlan(mealPlan);

            // Print to console
            System.out.println("Meal plan saved!");

            // Show success alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Meal plan saved successfully!");
            alert.showAndWait();
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

    private void setMealPlanForDay(MealPlan mealPlan, String day, ComboBox<String> breakfast, ComboBox<String> snack, ComboBox<String> snackEvening, ComboBox<String> lunch) {
        String breakfastId = getSelectedRecipeId(breakfast);
        String snackId = getSelectedRecipeId(snack);
        String snackEveningId = getSelectedRecipeId(snackEvening);
        String lunchId = getSelectedRecipeId(lunch);

        if (breakfastId == null || snackId == null || snackEveningId == null || lunchId == null) {
            System.out.println("Please select all meals for " + day);
            return;
        }

        mealPlan.setRecipeId(day, "breakfast", breakfastId);
        mealPlan.setRecipeId(day, "snack", snackId);
        mealPlan.setRecipeId(day, "snack2", snackEveningId); // Evening snack
        mealPlan.setRecipeId(day, "lunch", lunchId);
    }

    private String getSelectedRecipeId(ComboBox<String> choiceBox) {
        String selectedRecipeName = choiceBox.getSelectionModel().getSelectedItem();
        Recipe selectedRecipe = recipeDAO.getRecipeByName(selectedRecipeName);
        return selectedRecipe != null ? selectedRecipe.getRecipeId() : null;
    }

    @FXML
    private void handleCancelMealPlan() {
        System.out.println("Meal plan creation canceled!");
    }

}
