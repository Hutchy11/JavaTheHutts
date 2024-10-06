package com.example.demo.controller;

import com.example.demo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class MealPlanViewController {

    @FXML
    private ChoiceBox<String> mealPlanChoiceBox;

    @FXML
    private Label mealPlanDateLabel;

    // Individual TextField declarations
    @FXML
    private TextField textField1; // Monday Breakfast
    @FXML
    private TextField textField2; // Tuesday Breakfast
    @FXML
    private TextField textField3; // Wednesday Breakfast
    @FXML
    private TextField textField4; // Thursday Breakfast
    @FXML
    private TextField textField5; // Friday Breakfast

    @FXML
    private TextField textField6; // Monday Snack
    @FXML
    private TextField textField7; // Tuesday Snack
    @FXML
    private TextField textField8; // Wednesday Snack
    @FXML
    private TextField textField9; // Thursday Snack
    @FXML
    private TextField textField10; // Friday Snack

    @FXML
    private TextField textField11; // Monday Lunch
    @FXML
    private TextField textField12; // Tuesday Lunch
    @FXML
    private TextField textField13; // Wednesday Lunch
    @FXML
    private TextField textField14; // Thursday Lunch
    @FXML
    private TextField textField15; // Friday Lunch

    @FXML
    private TextField textField16; // Monday Evening Snack
    @FXML
    private TextField textField17; // Tuesday Evening Snack
    @FXML
    private TextField textField18; // Wednesday Evening Snack
    @FXML
    private TextField textField19; // Thursday Evening Snack
    @FXML
    private TextField textField20; // Friday Evening Snack

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

    private MealPlanDAO mealPlanDAO = new MealPlanDAO();
    private RecipeDAO recipeDAO = new RecipeDAO();


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

    public void setMealPlanDetails(String date) {
        System.out.println("Selected date: " + date); // Debug print statement
        MealPlan mealPlan = mealPlanDAO.getMealPlanByDate(date);
        if (mealPlan != null) {

            String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday"};
            String[] mealTypes = {"breakfast", "snack", "lunch", "snack2"};

            TextField[][] textFields = {
                    {textField1, textField6, textField11, textField16},
                    {textField2, textField7, textField12, textField17},
                    {textField3, textField8, textField13, textField18},
                    {textField4, textField9, textField14, textField19},
                    {textField5, textField10, textField15, textField20}
            };

            ImageView[][] imageViews = {
                    {imageViewBreakfastMonday, imageViewSnackMonday, imageViewLunchMonday, imageViewSnackMondayEvening},
                    {imageViewBreakfastTuesday, imageViewSnackTuesday, imageViewLunchTuesday, imageViewSnackTuesdayEvening},
                    {imageViewBreakfastWednesday, imageViewSnackWednesday, imageViewLunchWednesday, imageViewSnackWednesdayEvening},
                    {imageViewBreakfastThursday, imageViewSnackThursday, imageViewLunchThursday, imageViewSnackThursdayEvening},
                    {imageViewBreakfastFriday, imageViewSnackFriday, imageViewLunchFriday, imageViewSnackFridayEvening}
            };

            for (int i = 0; i < days.length; i++) {
                for (int j = 0; j < mealTypes.length; j++) {
                    String recipeId = mealPlan.getRecipeId(days[i], mealTypes[j]);
                    System.out.println("Day: " + days[i] + ", MealType: " + mealTypes[j] + ", RecipeId: " + recipeId); // Debug print statement
                    if (recipeId != null) {
                        Recipe recipe = recipeDAO.getRecipeById(recipeId);
                        if (recipe != null) {
                            textFields[i][j].setText(recipe.getRecipeName());
                            if (recipe.getRecipeImage() != null) {
                                Image image = new Image(new ByteArrayInputStream(recipe.getRecipeImage()));
                                imageViews[i][j].setImage(image);
                            }
                        }
                    }
                }
            }
        }
    }
}