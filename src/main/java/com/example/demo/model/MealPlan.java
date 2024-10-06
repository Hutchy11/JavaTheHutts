package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

public class MealPlan {
    private String mealPlanId;
    private String staffId;
    private String date;
    private Map<String, Map<String, String>> recipeIds; // Maps day to meal type to recipe ID
    private Map<String, Map<String, String>> mealImages;
    private Map<String, Map<String, String>> recipeNames; // Maps day to meal type to recipe name

    public MealPlan() {
        recipeIds = new HashMap<>();
        mealImages = new HashMap<>();
        recipeNames = new HashMap<>();
    }

    // Constructor to match the parameters being passed in MealPlanDAO
    public MealPlan(String mealPlanId, String staffId, String date, String recipeIds) {
        this.mealPlanId = mealPlanId;
        this.staffId = staffId;
        this.date = date;
        this.recipeIds = new HashMap<>();
        this.mealImages = new HashMap<>();
        this.recipeNames = new HashMap<>();
    }

    // Getters and setters
    public String getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(String mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public String getMealImage(String day, String meal) {
        return mealImages.getOrDefault(day, new HashMap<>()).get(meal);
    }

    public void setMealImage(String day, String meal, String imageUrl) {
        mealImages.computeIfAbsent(day, k -> new HashMap<>()).put(meal, imageUrl);
    }

    public String getRecipeId(String day, String meal) {
        return recipeIds.getOrDefault(day, new HashMap<>()).get(meal);
    }

    public void setRecipeId(String day, String meal, String recipeId) {
        recipeIds.computeIfAbsent(day, k -> new HashMap<>()).put(meal, recipeId);
    }

    public String getRecipeName(String day, String meal) {
        return recipeNames.getOrDefault(day, new HashMap<>()).get(meal);
    }

    public void setRecipeName(String day, String meal, String recipeName) {
        recipeNames.computeIfAbsent(day, k -> new HashMap<>()).put(meal, recipeName);
    }

    public Map<String, Map<String, String>> getRecipeIds() {
        return recipeIds;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}