package com.example.demo.model;

import java.util.ArrayList;

public class MockMealPlanDAO implements IMealPlanDAO {
    @Override
    public void insertMealPlan(MealPlan mealPlan) {

    }

    @Override
    public ArrayList<String> getAllRecipeIdsByDate(String date) {
        // Simulate returning a list of recipe IDs for a given date
        ArrayList<String> recipeIDs = new ArrayList<>();
        if (date.compareTo("2024-10-28") == 0) {
            recipeIDs.add("cdf60846-05ac-47d9-94e6-5c6c098c9e2a"); // Basic Omelette
            recipeIDs.add("cd3ce395-bf3a-414a-a545-c6f07c50999e"); // Banana Muffin
            recipeIDs.add("3bd4c8a3-cf55-4ff7-96ea-3398e008f0fc"); // Cheese and Vegetable Frittata
            recipeIDs.add("02f5c1d7-56cf-4d45-b120-342e2b72f36b"); // Chicken and salad wrap
            return  recipeIDs;
        }
        return new ArrayList<>();
    }


    public String getRecipeNameById(String recipeId) {
        //show all recipe id (RecipeDAO)
        if ("cdf60846-05ac-47d9-94e6-5c6c098c9e2a".compareTo(recipeId) == 0) {
            return "Basic Omelette";
        } else if ("cd3ce395-bf3a-414a-a545-c6f07c50999e".compareTo(recipeId) == 0) {
            return "Banana Muffin";
        } else if ("3bd4c8a3-cf55-4ff7-96ea-3398e008f0fc".compareTo(recipeId) == 0) {
            return "Cheese and Vegetable Frittata";
        } else if ("02f5c1d7-56cf-4d45-b120-342e2b72f36b".compareTo(recipeId) == 0) {
            return "Chicken and salad wrap";
        }
        return new String("");
    }

}

