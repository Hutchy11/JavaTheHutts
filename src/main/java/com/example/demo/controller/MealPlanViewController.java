package com.example.demo.controller;

import com.example.demo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Controller class for the Meal Plan View.
 * Handles the display and interaction with meal plans.
 */
public class MealPlanViewController {

    @FXML
    public Label mondayBreakfastLabel;
    @FXML
    public Label tuesdayBreakfastLabel;
    @FXML
    public Label wednesdayBreakfastLabel;
    @FXML
    public Label thursdayBreakfastLabel;
    @FXML
    public Label fridayBreakfastLabel;
    @FXML
    private Label mondaySnack1Label;
    @FXML
    private Label tuesdaySnack1Label;
    @FXML
    private Label wednesdaySnack1Label;
    @FXML
    private Label thursdaySnack1Label;
    @FXML
    private Label fridaySnack1Label;
    @FXML
    private Label mondayLunchLabel;
    @FXML
    private Label tuesdayLunchLabel;
    @FXML
    private Label wednesdayLunchLabel;
    @FXML
    private Label thursdayLunchLabel;
    @FXML
    private Label fridayLunchLabel;
    @FXML
    private Label mondaySnack2Label;
    @FXML
    private Label tuesdaySnack2Label;
    @FXML
    private Label wednesdaySnack2Label;
    @FXML
    private Label thursdaySnack2Label;
    @FXML
    private Label fridaySnack2Label;
    @FXML
    private ChoiceBox<String> mealPlanChoiceBox;

    // Individual ImageView declarations
    @FXML
    private ImageView imageViewBreakfastMonday, imageViewBreakfastTuesday, imageViewBreakfastWednesday;
    @FXML
    private ImageView imageViewBreakfastThursday, imageViewBreakfastFriday;
    @FXML
    private ImageView imageViewSnackMonday, imageViewSnackTuesday, imageViewSnackWednesday;
    @FXML
    private ImageView imageViewSnackThursday, imageViewSnackFriday;
    @FXML
    private ImageView imageViewLunchMonday, imageViewLunchTuesday, imageViewLunchWednesday;
    @FXML
    private ImageView imageViewLunchThursday, imageViewLunchFriday;
    @FXML
    private ImageView imageViewSnackMondayEvening, imageViewSnackTuesdayEvening, imageViewSnackWednesdayEvening;
    @FXML
    private ImageView imageViewSnackThursdayEvening, imageViewSnackFridayEvening;

    public MealPlanDAO mealPlanDAO = new MealPlanDAO();
    public RecipeDAO recipeDAO = new RecipeDAO();

    /**
     * Initializes the controller class.
     * Populates the meal plan choice box with available dates and sets up listeners.
     */
    @FXML
    public void initialize() {
        List<String> mealPlanDates = MealPlanDAO.getAllMealPlanDates();
        mealPlanChoiceBox.getItems().addAll(mealPlanDates);
        mealPlanChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setMealPlanDetails(newValue);
            }
        });
    }

    /**
     * Sets the meal plan details for the selected date.
     *
     * @param date the selected date
     */
    public void setMealPlanDetails(String date) {
        List<String> recipeIds = mealPlanDAO.getAllRecipeIdsByDate(date);
        System.out.println("Fetched recipe IDs: " + recipeIds);
        List<String> recipeNames = recipeDAO.getRecipeNameById(recipeIds);
        System.out.println("Fetched recipe names: " + recipeNames);

        if (recipeNames != null && recipeNames.size() >= 19) {
            // Set labels and images for each day of the week
            setLabelAndImage(mondayBreakfastLabel, imageViewBreakfastMonday, recipeNames.get(18));
            setLabelAndImage(tuesdayBreakfastLabel, imageViewBreakfastTuesday, recipeNames.get(2));
            setLabelAndImage(wednesdayBreakfastLabel, imageViewBreakfastWednesday, recipeNames.get(6));
            setLabelAndImage(thursdayBreakfastLabel, imageViewBreakfastThursday, recipeNames.get(10));
            setLabelAndImage(fridayBreakfastLabel, imageViewBreakfastFriday, recipeNames.get(14));

            setLabelAndImage(mondaySnack1Label, imageViewSnackMonday, recipeNames.get(19));
            setLabelAndImage(tuesdaySnack1Label, imageViewSnackTuesday, recipeNames.get(3));
            setLabelAndImage(wednesdaySnack1Label, imageViewSnackWednesday, recipeNames.get(11));
            setLabelAndImage(thursdaySnack1Label, imageViewSnackThursday, recipeNames.get(15));
            setLabelAndImage(fridaySnack1Label, imageViewSnackFriday, recipeNames.get(9));

            setLabelAndImage(mondayLunchLabel, imageViewLunchMonday, recipeNames.get(0));
            setLabelAndImage(tuesdayLunchLabel, imageViewLunchTuesday, recipeNames.get(4));
            setLabelAndImage(wednesdayLunchLabel, imageViewLunchWednesday, recipeNames.get(8));
            setLabelAndImage(thursdayLunchLabel, imageViewLunchThursday, recipeNames.get(12));
            setLabelAndImage(fridayLunchLabel, imageViewLunchFriday, recipeNames.get(16));

            setLabelAndImage(mondaySnack2Label, imageViewSnackMondayEvening, recipeNames.get(1));
            setLabelAndImage(tuesdaySnack2Label, imageViewSnackTuesdayEvening, recipeNames.get(5));
            setLabelAndImage(wednesdaySnack2Label, imageViewSnackWednesdayEvening, recipeNames.get(9));
            setLabelAndImage(thursdaySnack2Label, imageViewSnackThursdayEvening, recipeNames.get(13));
            setLabelAndImage(fridaySnack2Label, imageViewSnackFridayEvening, recipeNames.get(17));
        } else {
            System.out.println("Not enough recipe names found for the labels.");
        }
    }

    /**
     * Sets the label text and image view for a given recipe name.
     *
     * @param label the label to set the text
     * @param imageView the image view to set the image
     * @param recipeName the name of the recipe
     */
    private void setLabelAndImage(Label label, ImageView imageView, String recipeName) {
        label.setText(recipeName);
        Recipe recipe = recipeDAO.getRecipeByName(recipeName);
        if (recipe != null && recipe.getRecipeImage() != null) {
            Image image = new Image(new ByteArrayInputStream(recipe.getRecipeImage()));
            imageView.setImage(image);
        }
    }
}