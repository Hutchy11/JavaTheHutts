package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.util.List;

public class CreateMealPlanController {

    @FXML
    private ImageView logoImage;

    @FXML
    private HBox headerHbox;

    @FXML
    private VBox mainContent;

    @FXML
    private DatePicker datePicker;

    @FXML
    private GridPane mealPlanGrid;

    @FXML
    private ComboBox<String> choiceBoxBreakfastMonday;
    @FXML
    private ComboBox<String> choiceBoxBreakfastTuesday;
    @FXML
    private ComboBox<String> choiceBoxBreakfastWednesday;
    @FXML
    private ComboBox<String> choiceBoxBreakfastThursday;
    @FXML
    private ComboBox<String> choiceBoxBreakfastFriday;

    @FXML
    private ComboBox<String> choiceBoxSnackMonday;
    @FXML
    private ComboBox<String> choiceBoxSnackTuesday;
    @FXML
    private ComboBox<String> choiceBoxSnackWednesday;
    @FXML
    private ComboBox<String> choiceBoxSnackThursday;
    @FXML
    private ComboBox<String> choiceBoxSnackFriday;

    @FXML
    private ComboBox<String> choiceBoxLunchMonday;
    @FXML
    private ComboBox<String> choiceBoxLunchTuesday;
    @FXML
    private ComboBox<String> choiceBoxLunchWednesday;
    @FXML
    private ComboBox<String> choiceBoxLunchThursday;
    @FXML
    private ComboBox<String> choiceBoxLunchFriday;

    @FXML
    private ComboBox<String> choiceBoxSnackMondayEvening;
    @FXML
    private ComboBox<String> choiceBoxSnackTuesdayEvening;
    @FXML
    private ComboBox<String> choiceBoxSnackWednesdayEvening;
    @FXML
    private ComboBox<String> choiceBoxSnackThursdayEvening;
    @FXML
    private ComboBox<String> choiceBoxSnackFridayEvening;

    @FXML
    private ImageView imageViewBreakfastMonday;
    @FXML
    private ImageView imageViewBreakfastTuesday;
    @FXML
    private ImageView imageViewBreakfastWednesday;
    @FXML
    private ImageView imageViewBreakfastThursday;
    @FXML
    private ImageView imageViewBreakfastFriday;

    @FXML
    private ImageView imageViewSnackMonday;
    @FXML
    private ImageView imageViewSnackTuesday;
    @FXML
    private ImageView imageViewSnackWednesday;
    @FXML
    private ImageView imageViewSnackThursday;
    @FXML
    private ImageView imageViewSnackFriday;

    @FXML
    private ImageView imageViewLunchMonday;
    @FXML
    private ImageView imageViewLunchTuesday;
    @FXML
    private ImageView imageViewLunchWednesday;
    @FXML
    private ImageView imageViewLunchThursday;
    @FXML
    private ImageView imageViewLunchFriday;

    @FXML
    private ImageView imageViewSnackMondayEvening;
    @FXML
    private ImageView imageViewSnackTuesdayEvening;
    @FXML
    private ImageView imageViewSnackWednesdayEvening;
    @FXML
    private ImageView imageViewSnackThursdayEvening;
    @FXML
    private ImageView imageViewSnackFridayEvening;

    private RecipeDAO recipeDAO = new RecipeDAO();

    @FXML
    private void initialize() {
        // Populate Breakfast
        populateChoiceBox(choiceBoxBreakfastMonday, "Breakfast", imageViewBreakfastMonday);
        populateChoiceBox(choiceBoxBreakfastTuesday, "Breakfast", imageViewBreakfastTuesday);
        populateChoiceBox(choiceBoxBreakfastWednesday, "Breakfast", imageViewBreakfastWednesday);
        populateChoiceBox(choiceBoxBreakfastThursday, "Breakfast", imageViewBreakfastThursday);
        populateChoiceBox(choiceBoxBreakfastFriday, "Breakfast", imageViewBreakfastFriday);

        // Populate Snack (morning or afternoon)
        populateChoiceBox(choiceBoxSnackMonday, "Snack", imageViewSnackMonday);
        populateChoiceBox(choiceBoxSnackTuesday, "Snack", imageViewSnackTuesday);
        populateChoiceBox(choiceBoxSnackWednesday, "Snack", imageViewSnackWednesday);
        populateChoiceBox(choiceBoxSnackThursday, "Snack", imageViewSnackThursday);
        populateChoiceBox(choiceBoxSnackFriday, "Snack", imageViewSnackFriday);

        // Populate Lunch
        populateChoiceBox(choiceBoxLunchMonday, "Lunch", imageViewLunchMonday);
        populateChoiceBox(choiceBoxLunchTuesday, "Lunch", imageViewLunchTuesday);
        populateChoiceBox(choiceBoxLunchWednesday, "Lunch", imageViewLunchWednesday);
        populateChoiceBox(choiceBoxLunchThursday, "Lunch", imageViewLunchThursday);
        populateChoiceBox(choiceBoxLunchFriday, "Lunch", imageViewLunchFriday);

        // Populate Snack (evening or another type)
        populateChoiceBox(choiceBoxSnackMondayEvening, "Snack", imageViewSnackMondayEvening);
        populateChoiceBox(choiceBoxSnackTuesdayEvening, "Snack", imageViewSnackTuesdayEvening);
        populateChoiceBox(choiceBoxSnackWednesdayEvening, "Snack", imageViewSnackWednesdayEvening);
        populateChoiceBox(choiceBoxSnackThursdayEvening, "Snack", imageViewSnackThursdayEvening);
        populateChoiceBox(choiceBoxSnackFridayEvening, "Snack", imageViewSnackFridayEvening);
    }

    private void populateChoiceBox(ComboBox<String> choiceBox, String mealType, ImageView imageView) {
        List<Recipe> recipes = recipeDAO.getRecipesByMealType(mealType);
        System.out.println("Populating ChoiceBox for meal type: " + mealType);
        for (Recipe recipe : recipes) {
            System.out.println("Adding recipe: " + recipe.getRecipeName());
            choiceBox.getItems().add(recipe.getRecipeName());
        }

        choiceBox.setOnAction(event -> {
            String selectedRecipeName = choiceBox.getSelectionModel().getSelectedItem();
            Recipe selectedRecipe = recipeDAO.getRecipeByName(selectedRecipeName);
            if (selectedRecipe != null && selectedRecipe.getRecipeImage() != null) {
                Image image = new Image(new ByteArrayInputStream(selectedRecipe.getRecipeImage()));
                imageView.setImage(image);
            }
        });
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