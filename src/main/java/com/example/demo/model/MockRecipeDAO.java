package com.example.demo.model;


public class MockRecipeDAO extends RecipeDAO {

    @Override
    public Recipe getRecipeByName(String recipeName) {
        System.out.println("Fetching recipe for name: " + recipeName);
        switch (recipeName) {
            case "Recipe1":
                return new Recipe("1", "Recipe1", "Ingredients1", "Instructions1", "Breakfast", null);
            case "Recipe2":
                return new Recipe("2", "Recipe2", "Ingredients2", "Instructions2", "Breakfast", null);
            case "Recipe3":
                return new Recipe("3", "Recipe3", "Ingredients3", "Instructions3", "Breakfast", null);
            case "Recipe4":
                return new Recipe("4", "Recipe4", "Ingredients4", "Instructions4", "Breakfast", null);
            case "Recipe5":
                return new Recipe("5", "Recipe5", "Ingredients5", "Instructions5", "Breakfast", null);
            default:
                return null;  // Return null if no match found
        }
    }
}