package com.example.demo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for Recipe.
 * Handles database operations related to recipes.
 */
public class RecipeDAO implements IRecipeDAO {
    private Connection connection;

    /**
     * Constructor for the RecipeDAO class.
     * Initializes the database connection and creates the recipe table if it doesn't exist.
     */
    public RecipeDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:childcaredb.db");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the recipe table in the database if it doesn't exist.
     */
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

    /**
     * Inserts a new recipe into the database.
     *
     * @param recipe the Recipe object to be inserted
     */
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

    /**
     * Retrieves a recipe by its name from the database.
     *
     * @param recipeName the name of the recipe
     * @return the Recipe object if found, null otherwise
     */
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

    /**
     * Retrieves a list of recipe IDs for a given recipe name.
     *
     * @param recipeName the name of the recipe
     * @return an ArrayList of recipe IDs that match the given recipe name
     */
    @Override
    public ArrayList<String> getRecipeIdByName(String recipeName) {
        ArrayList<String> recipeIds = new ArrayList<>();
        String sql = "SELECT RecipeId FROM Recipe WHERE RecipeName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recipeName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    recipeIds.add(rs.getString("RecipeId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeIds;
    }

    /**
     * Retrieves a list of ingredients for a given recipe name.
     *
     * @param recipeName the name of the recipe
     * @return an ArrayList of ingredients that match the given recipe name
     */
    @Override
    public ArrayList<String> getIngredientsByName(String recipeName) {
        ArrayList<String> ingredients = new ArrayList<>();
        String sql = "SELECT Ingredients FROM Recipe WHERE RecipeName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recipeName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ingredients.add(rs.getString("Ingredients"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    /**
     * Retrieves a list of instructions for a given recipe name.
     *
     * @param recipeName the name of the recipe
     * @return an ArrayList of instructions that match the given recipe name
     */
    @Override
    public ArrayList<String> getInstructionsByName(String recipeName) {
        ArrayList<String> instructions = new ArrayList<>();
        String sql = "SELECT Instructions FROM Recipe WHERE RecipeName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recipeName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    instructions.add(rs.getString("Instructions"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructions;
    }

    /**
     * Retrieves a list of meal types for a given recipe name.
     *
     * @param recipeName the name of the recipe
     * @return an ArrayList of meal types that match the given recipe name
     */
    @Override
    public ArrayList<String> getMealTypeByName(String recipeName) {
        ArrayList<String> mealType = new ArrayList<>();
        String sql = "SELECT MealType FROM Recipe WHERE RecipeName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recipeName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    mealType.add(rs.getString("MealType"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealType;
    }

    /**
     * Retrieves recipes by meal type from the database.
     *
     * @param mealType the type of meal (e.g., breakfast, lunch, dinner)
     * @return a list of Recipe objects
     */
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

    /**
     * Retrieves recipe names by their IDs from the database.
     *
     * @param recipeIds a list of recipe IDs
     * @return a list of recipe names
     */
    @Override
    public List<String> getRecipeNameById(List<String> recipeIds) {
        List<String> recipeNames = new ArrayList<>();

        String sql = "SELECT RecipeName FROM Recipe WHERE RecipeId = ?";

        // Loop through each recipeId and retrieve the corresponding recipe name
        for (String recipeId : recipeIds) {
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, recipeId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String recipeName = rs.getString("RecipeName");
                        recipeNames.add(recipeName);  // Add the recipe name to the list
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(recipeNames);
        return recipeNames;  // Return the list of recipe names
    }

    /**
     * Retrieves all recipe names from the database.
     *
     * @return a list of all recipe names
     */
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