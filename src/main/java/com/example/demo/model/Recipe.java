package com.example.demo.model;

import java.util.UUID;

/**
 * Model class representing a Recipe.
 * Contains information about a recipe and provides methods to access and modify this information.
 */
public class Recipe {
    private String recipeId;    // UUID for recipeId
    private String recipeName;
    private String ingredients;
    private String instructions;
    private String mealType;
    private byte[] recipeImage; // To store the recipe image as a byte array


    /**
     * Parameterized constructor for the Recipe class.
     * Initializes a new Recipe object with the specified details.
     *
     * @param recipeId the unique ID of the recipe, if null a new UUID will be generated
     * @param recipeName the name of the recipe
     * @param ingredients the ingredients of the recipe
     * @param instructions the instructions for the recipe
     * @param mealType the type of meal (e.g., breakfast, lunch, dinner)
     * @param recipeImage the image of the recipe as a byte array
     */
    public Recipe(String recipeId, String recipeName, String ingredients, String instructions, String mealType, byte[] recipeImage) {
        this.recipeId = (recipeId != null) ? recipeId : generateUUID(); // Use provided recipeId or generate a new one
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.mealType = mealType;
        this.recipeImage = recipeImage; // Store image as byte array
    }

    /**
     * Generates a new UUID for the recipe.
     *
     * @return a new UUID as a string
     */
    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public byte[] getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(byte[] recipeImage) {
        this.recipeImage = recipeImage;
    }

}
