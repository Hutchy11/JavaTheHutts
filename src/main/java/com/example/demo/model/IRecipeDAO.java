package com.example.demo.model;

import java.util.List;

public interface IRecipeDAO {
    void insertRecipe(Recipe recipe);

    // Method to retrieve a recipe by its name
    Recipe getRecipeByName(String recipeId);

    List<String> getRecipeNameById(List<String> recipeIds);
}
