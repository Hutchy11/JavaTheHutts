package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

public class MockMealPlanDAO extends MealPlanDAO{
    @Override
    public List<String> getAllRecipeIdsByDate(String date) {
        // Simulate returning a list of recipe IDs for a given date
        return Arrays.asList("1", "2", "3", "4", "5");
    }
}

