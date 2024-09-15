package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

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

    @FXML
    private Button selectImageButton; // Button to select an image

    private RecipeDAO recipeDAO = new RecipeDAO(); // Initialize RecipeDAO
    private byte[] recipeImage; // Byte array to store the selected image

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

    @FXML
    protected void onSubmitButtonClick() throws IOException {
        String recipeName = recipeNameField.getText().trim();
        String ingredients = ingredientsTextArea.getText().trim();
        String instructions = instructionsTextArea.getText().trim();
        String mealType = recipeChoiceBox.getSelectionModel().getSelectedItem();

        // Create a new Recipe object with the provided data
        Recipe recipe = new Recipe(UUID.randomUUID().toString(), recipeName, ingredients, instructions, mealType, recipeImage);

        // Insert the recipe into the database
        recipeDAO.insertRecipe(recipe);

        // Clear the form fields after successful insertion
        clearFields();

        System.out.println("Recipe has been submitted successfully!");

    }

    // Method to handle the image selection
    @FXML
    protected void onSelectImageButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(selectImageButton.getScene().getWindow());

        if (selectedFile != null) {
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                recipeImage = fis.readAllBytes();
                Image image = new Image(new FileInputStream(selectedFile));
                recipeImageView.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Clear the input fields after successful submission
    private void clearFields() {
        recipeNameField.clear();
        ingredientsTextArea.clear();
        instructionsTextArea.clear();
        recipeChoiceBox.getSelectionModel().clearSelection();
        recipeImageView.setImage(null);
        recipeImage = null;
    }

}
