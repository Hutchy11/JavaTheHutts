package com.example.demo.model;

import java.util.List;

public interface IRecipeDAO {
    void insertRecipe(Recipe recipe);
    Recipe getRecipeById(String recipeId);

    // Method to retrieve a recipe by its name
    Recipe getRecipeByName(String recipeId);

    List<Recipe> getAllRecipes();
    void updateRecipe(Recipe recipe);
    void deleteRecipe(String recipeId);
}
