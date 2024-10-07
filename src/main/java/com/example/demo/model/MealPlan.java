package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Model class representing a Meal Plan.
 * Contains information about a meal plan and provides methods to access and modify this information.
 */
public class MealPlan {
    private String mealPlanId;
    private String staffId;
    private String date;
    private Map<String, Map<String, String>> recipeIds; // Maps day to meal type to recipe ID
    private Map<String, Map<String, String>> mealImages;
    private Map<String, Map<String, String>> recipeNames; // Maps day to meal type to recipe name

    /**
     * Default constructor for the MealPlan class.
     * Initializes the maps for recipe IDs, meal images, and recipe names.
     */
    public MealPlan() {
        recipeIds = new HashMap<>();
        mealImages = new HashMap<>();
        recipeNames = new HashMap<>();
    }

    /**
     * Constructor for the MealPlan class.
     * Initializes a new MealPlan object with the specified details.
     *
     * @param mealPlanId the unique ID of the meal plan
     * @param staffId the ID of the staff member who created the meal plan
     * @param date the date of the meal plan
     * @param recipeIds the recipe IDs for the meal plan
     */
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

    public String getRecipeId(String day, String meal) {
        return recipeIds.getOrDefault(day, new HashMap<>()).get(meal);
    }

    public void setRecipeId(String day, String meal, String recipeId) {
        recipeIds.computeIfAbsent(day, k -> new HashMap<>()).put(meal, recipeId);
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