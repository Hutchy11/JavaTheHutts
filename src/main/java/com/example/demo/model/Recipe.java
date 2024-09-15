package com.example.demo.model;

import java.util.UUID;

public class Recipe {
    private String recipeId;    // UUID for recipeId
    private String ingredients;
    private String instructions;
    private String mealType;
    private byte[] recipeImage; // To store the recipe image as a byte array


    // Parameterized constructor (with image)
    public Recipe(String recipeId, String ingredients, String instructions, String mealType, byte[] recipeImage) {
        this.recipeId = (recipeId != null) ? recipeId : generateUUID(); // Use provided recipeId or generate a new one
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.mealType = mealType;
        this.recipeImage = recipeImage; // Store image as byte array
    }

    // Method to generate a UUID
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
