package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public interface IRecipeDAO {
    void insertRecipe(Recipe recipe);

    // Method to retrieve a recipe by its name
    Recipe getRecipeByName(String recipeId);

    List<String> getRecipeNameById(List<String> recipeIds);

    ArrayList<String> getRecipeIdByName(String recipeName);

    ArrayList<String> getIngredientsByName(String recipeName);

    ArrayList<String> getInstructionsByName(String recipeName);

    ArrayList<String> getMealTypeByName(String recipeName);
}
