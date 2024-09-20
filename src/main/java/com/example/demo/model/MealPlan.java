package com.example.demo.model;

public class MealPlan {
    private String mealPlanId;
    private String staffId;
    private String date;
    private String recipeId;
    private String notes;

    // Getters and setters
    public String getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(String mealPlanId) {
        this.mealPlanId = mealPlanId;
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

    public String getRecipeId(String day, String meal) {
        // Implement logic to return the correct recipe ID based on day and meal
        return recipeId;
    }

    public void setRecipeId(String day, String meal, String recipeId) {
        // Implement logic to set the correct recipe ID based on day and meal
        this.recipeId = recipeId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}