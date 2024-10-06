package com.example.demo.model;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class MealPlanDAO implements IMealPlanDAO {
    private Connection connection;

    public MealPlanDAO() {
        try {
            // Initialize the database connection
            connection = DriverManager.getConnection("jdbc:sqlite:childcaredb.db");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create table if it doesn't exist
    public void createTable() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS MealPlan (
                MealPlanId TEXT PRIMARY KEY,
                StaffId TEXT NOT NULL,
                Date TEXT NOT NULL,
                RecipeId TEXT
            );
        """;
        try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMealPlan(MealPlan mealPlan) {
        String mealPlanId = mealPlan.getMealPlanId();
        String recipeIds = mealPlan.getRecipeIds().values().stream()
                .flatMap(dayRecipes -> dayRecipes.values().stream())
                .collect(Collectors.joining(","));

        String sql = "INSERT INTO MealPlan (MealPlanId, StaffId, Date, RecipeId) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:childcaredb.db");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mealPlanId);
            pstmt.setString(2, mealPlan.getStaffId());
            pstmt.setString(3, mealPlan.getDate());
            pstmt.setString(4, recipeIds);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllMealPlanDates() {
        List<String> mealPlanDates = new ArrayList<>();
        String query = "SELECT DISTINCT date FROM MealPlan";

        try (Connection connection = SqliteConnection.getInstance();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                mealPlanDates.add(resultSet.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealPlanDates;
    }

    // Method to retrieve a meal plan by its ID
    @Override
    public MealPlan getMealPlanById(String mealPlanId) {
        return null; // No meal plan found
    }

    // Method to retrieve a meal plan by its date
    @Override
    public MealPlan getMealPlanByDate(String date) {
        MealPlan mealPlan = null;
        String sql = "SELECT * FROM MealPlan WHERE Date = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, date);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    mealPlan = new MealPlan(
                            rs.getString("MealPlanId"),
                            rs.getString("StaffId"),
                            rs.getString("Date"),
                            rs.getString("RecipeId")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealPlan;
    }

    @Override
    public List<MealPlan> getAllMealPlans() {
        List<MealPlan> mealPlans = new ArrayList<>();
        String sql = "SELECT mp.MealPlanId, mp.StaffId, mp.Date, r.RecipeId, r.RecipeName, r.MealType, r.RecipeImage " +
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealPlans;
    }

    @Override
    public void updateMealPlan(MealPlan mealPlan) {
        // Implementation here
    }

    @Override
    public void deleteMealPlan(String mealPlanId) {
        // Implementation here
    }
}