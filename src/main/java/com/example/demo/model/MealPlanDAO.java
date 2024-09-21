package com.example.demo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MealPlanDAO {
    private Connection connection;

    public MealPlanDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveMealPlan(MealPlan mealPlan) throws SQLException {
        String sql = "INSERT INTO MealPlan (MealPlanId, StaffId, Date, Notes) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mealPlan.getMealPlanId());
            statement.setString(2, mealPlan.getStaffId());
            statement.setString(3, mealPlan.getDate());
            statement.setString(4, mealPlan.getNotes());
            statement.executeUpdate();
        }

        // Save recipes associated with this meal plan
        for (String day : mealPlan.getRecipeIds().keySet()) {
            for (String mealType : mealPlan.getRecipeIds().get(day).keySet()) {
                String recipeId = mealPlan.getRecipeId(day, mealType);
                // Save the recipe to the Recipe table, if needed.
            }
        }
    }


    public MealPlan getMealPlanById(String mealPlanId) throws SQLException {
        String sql = "SELECT * FROM MealPlan WHERE MealPlanId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mealPlanId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                MealPlan mealPlan = new MealPlan();
                mealPlan.setMealPlanId(resultSet.getString("MealPlanId"));
                mealPlan.setStaffId(resultSet.getString("StaffId"));
                mealPlan.setDate(resultSet.getString("Date"));
                mealPlan.setRecipeId("day", "meal", resultSet.getString("RecipeId")); // Adjust as needed
                mealPlan.setNotes(resultSet.getString("Notes"));
                return mealPlan;
            }
        }
        return null; // No meal plan found
    }

    public List<MealPlan> getAllMealPlans() throws SQLException {
        List<MealPlan> mealPlans = new ArrayList<>();
        String sql = "SELECT mp.MealPlanId, mp.StaffId, mp.Date, mp.Notes, r.RecipeId, r.RecipeName, r.MealType, r.RecipeImage " +
                "FROM MealPlan mp " +
                "LEFT JOIN Recipe r ON mp.MealPlanId = r.MealPlanId";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Map<String, MealPlan> mealPlanMap = new HashMap<>();

            while (resultSet.next()) {
                String mealPlanId = resultSet.getString("MealPlanId");
                MealPlan mealPlan = mealPlanMap.get(mealPlanId);
                if (mealPlan == null) {
                    mealPlan = new MealPlan();
                    mealPlan.setMealPlanId(mealPlanId);
                    mealPlan.setStaffId(resultSet.getString("StaffId"));
                    mealPlan.setDate(resultSet.getString("Date"));
                    mealPlan.setNotes(resultSet.getString("Notes"));
                    mealPlanMap.put(mealPlanId, mealPlan);
                }

                String recipeId = resultSet.getString("RecipeId");
                String recipeName = resultSet.getString("RecipeName");
                String mealType = resultSet.getString("MealType");
                String imageUrl = resultSet.getString("RecipeImage") != null ? "data:image/png;base64," + Base64.getEncoder().encodeToString(resultSet.getBytes("RecipeImage")) : null;

                // Set the recipe details in the meal plan
                if (recipeId != null) {
                    mealPlan.setRecipeId("day", mealType, recipeId); // Replace "day" with actual day value
                    mealPlan.setRecipeName("day", mealType, recipeName); // Replace "day" with actual day value
                    mealPlan.setMealImage("day", mealType, imageUrl); // Replace "day" with actual day value
                }
            }

            mealPlans.addAll(mealPlanMap.values());
        }
        return mealPlans;
    }


}