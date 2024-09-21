package com.example.demo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MealPlanService {
    private MealPlanDAO mealPlanDAO;
    private Connection connection;

    public MealPlanService() {
        try {
            // Initialize the database connection
            connection = DriverManager.getConnection("jdbc:sqlite:childcaredb.db");
            mealPlanDAO = new MealPlanDAO(connection);
            // Optionally, create tables or any initialization logic here
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch all meal plans
    public List<MealPlan> getAllMealPlans() throws SQLException {
        return mealPlanDAO.getAllMealPlans(); // Implement this method in MealPlanDAO
    }

    // Don't forget to close the connection when done
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
