package com.example.demo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MealPlanDAO {
    private Connection connection;

    public MealPlanDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveMealPlan(MealPlan mealPlan) throws SQLException {
        String sql = "INSERT INTO MealPlan (MealPlanId, StaffId, Date, RecipeId, Notes) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mealPlan.getMealPlanId());
            statement.setString(2, mealPlan.getStaffId());
            statement.setString(3, mealPlan.getDate());
            statement.setString(4, mealPlan.getRecipeId());
            statement.setString(5, mealPlan.getNotes());
            statement.executeUpdate();
        }
    }
}