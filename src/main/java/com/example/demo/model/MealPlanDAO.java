package com.example.demo.model;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Data Access Object (DAO) class for MealPlan.
 * Handles database operations related to meal plans.
 */
public class MealPlanDAO implements IMealPlanDAO {
    private Connection connection;

    /**
     * Constructor for the MealPlanDAO class.
     * Initializes the database connection and creates the meal plan table if it doesn't exist.
     */
    public MealPlanDAO() {
        try {
            // Initialize the database connection
            connection = DriverManager.getConnection("jdbc:sqlite:childcaredb.db");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the meal plan table in the database if it doesn't exist.
     */
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

    /**
     * Inserts a new meal plan into the database.
     *
     * @param mealPlan the MealPlan object to be inserted
     */
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

    /**
     * Fetches all distinct meal plan dates from the database.
     *
     * @return a list of meal plan dates
     */
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

    /**
     * Fetches all recipe IDs for a given date from the database.
     *
     * @param date the date for which to fetch recipe IDs
     * @return a list of recipe IDs
     */
    @Override
    public List<String> getAllRecipeIdsByDate(String date) {
        List<String> recipeIds = new ArrayList<>();
        String sql = "SELECT RecipeId FROM MealPlan WHERE Date = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, date);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String recipeIdField = rs.getString("RecipeId");

                    // Split RecipeId if comma-separated
                    String[] individualIds = recipeIdField.split(",");
                    for (String id : individualIds) {
                        recipeIds.add(id.trim());  // Trim to remove any whitespace
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(recipeIds);
        return recipeIds;
    }

}