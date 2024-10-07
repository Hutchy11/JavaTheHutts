package com.example.demo.model;

import java.util.List;

public interface IMealPlanDAO {
    void insertMealPlan(MealPlan mealPlan);

    List<String> getAllRecipeIdsByDate(String date);

}
