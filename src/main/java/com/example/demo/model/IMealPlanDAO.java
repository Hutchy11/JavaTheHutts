package com.example.demo.model;

import java.util.List;

public interface IMealPlanDAO {
    void insertMealPlan(MealPlan mealPlan);
    MealPlan getMealPlanById(String mealPlanId);

    // Method to retrieve a meal plan by its date
    MealPlan getMealPlanByDate(String mealPlanId);

    List<MealPlan> getAllMealPlans();
    void updateMealPlan(MealPlan mealPlan);
    void deleteMealPlan(String mealPlanId);
}
