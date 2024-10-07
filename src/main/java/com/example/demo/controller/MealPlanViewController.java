package com.example.demo.controller;

import com.example.demo.model.MealPlan;
import com.example.demo.model.MealPlanService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.List;

public class MealPlanViewController {

    @FXML
    private ComboBox<String> mealPlanComboBox;

    @FXML
    private TextField dateField;

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

    @FXML
    private final TextField[] breakfastFields = new TextField[5]; // Index 0-4 for days
    @FXML
    private final TextField[] snackFields = new TextField[5];
    @FXML
    private final TextField[] lunchFields = new TextField[5];
    @FXML
    private final TextField[] eveningSnackFields = new TextField[5];

    @FXML
    private final ImageView[] breakfastImageViews = new ImageView[5];
    @FXML
    private final ImageView[] snackImageViews = new ImageView[5];
    @FXML
    private final ImageView[] lunchImageViews = new ImageView[5];
    @FXML
    private final ImageView[] eveningSnackImageViews = new ImageView[5];

    private MealPlanService mealPlanService;
    private List<MealPlan> mealPlans;

    @FXML
    public void initialize() throws SQLException {
        mealPlanService = new MealPlanService();
        loadMealPlans();  // Load meal plans into ComboBox
        mealPlanComboBox.setOnAction(event -> handleMealPlanSelection());

        // Initialize the arrays with the corresponding fields and image views
        initializeFieldsAndImages();
    }

    private void initializeFieldsAndImages() {
        breakfastFields[0] = textField1;
        breakfastFields[1] = textField2;
        breakfastFields[2] = textField3;
        breakfastFields[3] = textField4;
        breakfastFields[4] = textField5;

        snackFields[0] = textField6;
        snackFields[1] = textField7;
        snackFields[2] = textField8;
        snackFields[3] = textField9;
        snackFields[4] = textField10;

        lunchFields[0] = textField11;
        lunchFields[1] = textField12;
        lunchFields[2] = textField13;
        lunchFields[3] = textField14;
        lunchFields[4] = textField15;

        eveningSnackFields[0] = textField16;
        eveningSnackFields[1] = textField17;
        eveningSnackFields[2] = textField18;
        eveningSnackFields[3] = textField19;
        eveningSnackFields[4] = textField20;

        breakfastImageViews[0] = imageViewBreakfastMonday;
        breakfastImageViews[1] = imageViewBreakfastTuesday;
        breakfastImageViews[2] = imageViewBreakfastWednesday;
        breakfastImageViews[3] = imageViewBreakfastThursday;
        breakfastImageViews[4] = imageViewBreakfastFriday;

        snackImageViews[0] = imageViewSnackMonday;
        snackImageViews[1] = imageViewSnackTuesday;
        snackImageViews[2] = imageViewSnackWednesday;
        snackImageViews[3] = imageViewSnackThursday;
        snackImageViews[4] = imageViewSnackFriday;

        lunchImageViews[0] = imageViewLunchMonday;
        lunchImageViews[1] = imageViewLunchTuesday;
        lunchImageViews[2] = imageViewLunchWednesday;
        lunchImageViews[3] = imageViewLunchThursday;
        lunchImageViews[4] = imageViewLunchFriday;

        eveningSnackImageViews[0] = imageViewSnackMondayEvening;
        eveningSnackImageViews[1] = imageViewSnackTuesdayEvening;
        eveningSnackImageViews[2] = imageViewSnackWednesdayEvening;
        eveningSnackImageViews[3] = imageViewSnackThursdayEvening;
        eveningSnackImageViews[4] = imageViewSnackFridayEvening;
    }

    private void loadMealPlans() throws SQLException {
        mealPlans = mealPlanService.getAllMealPlans();
        for (int i = 0; i < mealPlans.size(); i++) {
            MealPlan plan = mealPlans.get(i);
            mealPlanComboBox.getItems().add("Meal Plan " + (i + 1) + " (ID: " + plan.getMealPlanId() + ")");
        }
    }

    private void handleMealPlanSelection() {
        String selectedMealPlan = mealPlanComboBox.getValue();
        if (selectedMealPlan != null) {
            int index = mealPlanComboBox.getSelectionModel().getSelectedIndex();
            MealPlan selectedPlan = mealPlans.get(index);
            clearAllFieldsAndImages(); // Clear fields before populating
            populateFieldsAndImages(selectedPlan);
        }
    }

    private void populateFieldsAndImages(MealPlan mealPlan) {
        dateField.setText(mealPlan.getDate());

        for (int i = 0; i < 5; i++) {
            String day = getDay(i);
            setRecipeDetails(day, "Breakfast", breakfastFields[i], breakfastImageViews[i], mealPlan);
            setRecipeDetails(day, "Snack", snackFields[i], snackImageViews[i], mealPlan);
            setRecipeDetails(day, "Lunch", lunchFields[i], lunchImageViews[i], mealPlan);
            setRecipeDetails(day, "Evening Snack", eveningSnackFields[i], eveningSnackImageViews[i], mealPlan);
        }
    }

    private String getDay(int index) {
        switch (index) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            default:
                return "";
        }
    }

    private void setRecipeDetails(String day, String meal, TextField textField, ImageView imageView, MealPlan mealPlan) {
        String recipeName = mealPlan.getRecipeName(day, meal); // Adjust method based on your MealPlan model
        textField.setText(recipeName);
        setImage(imageView, mealPlan.getMealImage(day, meal)); // Adjust method based on your MealPlan model
    }

    private void setImage(ImageView imageView, String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            imageView.setImage(new Image(imageUrl));
        } else {
            imageView.setImage(null); // or set a default image
        }
    }

    private void clearAllFieldsAndImages() {
        clearAllTextFields();
        clearAllImageViews();
    }

    private void clearAllTextFields() {
        for (TextField textField : breakfastFields) textField.clear();
        for (TextField textField : snackFields) textField.clear();
        for (TextField textField : lunchFields) textField.clear();
        for (TextField textField : eveningSnackFields) textField.clear();
    }

    private void clearAllImageViews() {
        for (ImageView imageView : breakfastImageViews) imageView.setImage(null);
        for (ImageView imageView : snackImageViews) imageView.setImage(null);
        for (ImageView imageView : lunchImageViews) imageView.setImage(null);
        for (ImageView imageView : eveningSnackImageViews) imageView.setImage(null);
    }
}
