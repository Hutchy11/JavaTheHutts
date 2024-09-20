package com.example.demo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO implements IRecipeDAO {
    private Connection connection;

    public RecipeDAO() {
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
        String sql = """
            CREATE TABLE IF NOT EXISTS Recipe (
                RecipeId TEXT PRIMARY KEY,
                MealPlanId TEXT NOT NULL,
                RecipeName TEXT NOT NULL,
                MealType TEXT NOT NULL,
                RecipeImage BLOB,
                Ingredients TEXT NOT NULL,
                Instructions TEXT NOT NULL,
                PRIMARY KEY("RecipeId"),
                FOREIGN KEY("MealPlanId") REFERENCES "MealPlan"("MealPlanId")
            );
        """;
        try (Statement stmt = connection.createStatement()) {
            // Execute the SQL statement to create the table
            stmt.execute(sql);
            System.out.println("Recipe table created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating Recipe table: " + e.getMessage());
        }
    }

    // Method to insert a new recipe into the database
    @Override
    public void insertRecipe(Recipe recipe) {
        String sql = "INSERT INTO Recipe (RecipeId, RecipeName, MealType, RecipeImage, Ingredients, Instructions) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recipe.getRecipeId());
            pstmt.setString(2, recipe.getRecipeName());
            pstmt.setString(3, recipe.getMealType());
            pstmt.setBytes(4, recipe.getRecipeImage());
            pstmt.setString(5, recipe.getIngredients());
            pstmt.setString(6, recipe.getInstructions());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to retrieve a recipe by its ID
    @Override
    public Recipe getRecipeById(String recipeId) {
        return null;
    }

    // Method to retrieve a recipe by its name
    @Override
    public Recipe getRecipeByName(String recipeName) {
        Recipe recipe = null;
        String sql = "SELECT * FROM Recipe WHERE RecipeName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recipeName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    recipe = new Recipe(
                            rs.getString("RecipeId"),
                            rs.getString("RecipeName"),
                            rs.getString("Ingredients"),
                            rs.getString("Instructions"),
                            rs.getString("MealType"),
                            rs.getBytes("RecipeImage")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    // Method to get recipes by meal type
    public List<Recipe> getRecipesByMealType(String mealType) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipe WHERE MealType = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mealType);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Recipe recipe = new Recipe(
                        rs.getString("RecipeId"),
                        rs.getString("RecipeName"),
                        rs.getString("Ingredients"),
                        rs.getString("Instructions"),
                        rs.getString("MealType"),
                        rs.getBytes("RecipeImage")
                );
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    // Method to retrieve all recipes from the database
    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipe";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Recipe recipe = new Recipe(
                        rs.getString("RecipeId"),
                        rs.getString("RecipeName"),
                        rs.getString("Ingredients"),
                        rs.getString("Instructions"),
                        rs.getString("MealType"),
                        rs.getBytes("RecipeImage")
                );
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    // Method to update a recipe in the database
    @Override
    public void updateRecipe(Recipe recipe) {
        // Implementation here
    }

    // Method to delete a recipe from the database
    @Override
    public void deleteRecipe(String recipeId) {
        // Implementation here
    }

    public List<String> getAllRecipeNames() {
        List<String> recipeNames = new ArrayList<>();
        String sql = "SELECT RecipeName FROM Recipe";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                recipeNames.add(rs.getString("RecipeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeNames;
    }
}