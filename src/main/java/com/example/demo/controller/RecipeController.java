package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RecipeController {

    @FXML
    private ChoiceBox<String> recipeChoiceBox; // Dropdown for recipe selection

    @FXML
    private TextField recipeNameField; // Input field for recipe name

    @FXML
    private ImageView recipeImageView; // Image for the selected recipe

    @FXML
    private TextArea ingredientsTextArea; // Text area for ingredients

    @FXML
    private TextArea instructionsTextArea; // Text area for instructions

    @FXML
    private Button submitButton; // Button to submit the recipe

    // Protected method to check if all fields are completed and enable submit button
    @FXML
    protected void checkFieldsCompletion() {
        // Check if all the fields are filled (not empty)
        boolean isRecipeNameFilled = !recipeNameField.getText().trim().isEmpty();
        boolean isIngredientsFilled = !ingredientsTextArea.getText().trim().isEmpty();
        boolean isInstructionsFilled = !instructionsTextArea.getText().trim().isEmpty();
        boolean isChoiceBoxSelected = recipeChoiceBox.getSelectionModel().getSelectedItem() != null;

        // Enable the Submit button if all fields are filled and ChoiceBox has a selection
        submitButton.setDisable(!(isRecipeNameFilled && isIngredientsFilled && isInstructionsFilled && isChoiceBoxSelected));
    }

    // Initialize method to set up default behavior
    @FXML
    public void initialize() {
        // Disable the submit button by default
        submitButton.setDisable(true);
        // Add choices to the ChoiceBox
        recipeChoiceBox.getItems().addAll("Breakfast", "Lunch", "Snack");

        // Add listeners to enable submit button when all fields are filled
        recipeNameField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsCompletion());
        ingredientsTextArea.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsCompletion());
        instructionsTextArea.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsCompletion());
        recipeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> checkFieldsCompletion());
    }

}
