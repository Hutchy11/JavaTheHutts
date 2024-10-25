package com.example.demo.model;


import java.util.ArrayList;
import java.util.List;

public class MockRecipeDAO implements IRecipeDAO {
    @Override
    public void insertRecipe(Recipe recipe) {
    }

    public ArrayList<String> getRecipeIdByName(String recipeName) {
        // Simulate returning a list of recipe IDs for a given recipe name
        ArrayList<String> recipeIds = new ArrayList<>();
        if ("Basic Omellete".compareTo(recipeName) == 0) {
            recipeIds.add("cdf60846-05ac-47d9-94e6-5c6c098c9e2a");
            return recipeIds;
        } else if ("Banana Muffin".compareTo(recipeName) == 0) {
            recipeIds.add("cd3ce395-bf3a-414a-a545-c6f07c50999e");
            return recipeIds;
        } else if ("Cheese and Vegetable Frittata".compareTo(recipeName) == 0) {
            recipeIds.add("3bd4c8a3-cf55-4ff7-96ea-3398e008f0fc");
            return recipeIds;
        } else if ("Chicken and salad wrap".compareTo(recipeName) == 0) {
            recipeIds.add("02f5c1d7-56cf-4d45-b120-342e2b72f36b");
            return recipeIds;
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getIngredientsByName(String recipeName) {
        ArrayList<String> ingredients = new ArrayList<>();
        if ("Basic Omelette".compareTo(recipeName) == 0) {
            ingredients.add("Eggs");
            return ingredients;
        } else if ("Banana Muffin".compareTo(recipeName) == 0) {
            ingredients.add("Bananas");
            return ingredients;
        } else if ("Cheese and Vegetable Frittata".compareTo(recipeName) == 0) {
            ingredients.add("Cheese");
            return ingredients;
        } else if ("Chicken and salad wrap".compareTo(recipeName) == 0) {
            ingredients.add("Chicken");
            return ingredients;
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getInstructionsByName(String recipeName) {
        ArrayList<String> instructions = new ArrayList<>();
        if ("Basic Omelette".compareTo(recipeName) == 0) {
            instructions.add("Beat eggs");
            return instructions;
        } else if ("Banana Muffin".compareTo(recipeName) == 0) {
            instructions.add("Peel bananas");
            return instructions;
        } else if ("Cheese and Vegetable Frittata".compareTo(recipeName) == 0) {
            instructions.add("Grate cheese");
            return instructions;
        } else if ("Chicken and salad wrap".compareTo(recipeName) == 0) {
            instructions.add("Shred chicken");
            return instructions;
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getMealTypeByName(String recipeName) {
        ArrayList<String> mealType = new ArrayList<>();
        if ("Basic Omelette".compareTo(recipeName) == 0) {
            mealType.add("Breakfast");
            return mealType;
        } else if ("Banana Muffin".compareTo(recipeName) == 0) {
            mealType.add("Snack");
            return mealType;
        } else if ("Cheese and Vegetable Frittata".compareTo(recipeName) == 0) {
            mealType.add("Lunch");
            return mealType;
        } else if ("Chicken and salad wrap".compareTo(recipeName) == 0) {
            mealType.add("Lunch");
            return mealType;
        }
        return new ArrayList<>();
    }

    @Override
    public Recipe getRecipeByName(String recipeName) {
        if ("Basic Omelette".compareTo(recipeName) == 0) {
                return new Recipe("cdf60846-05ac-47d9-94e6-5c6c098c9e2a", "Basic Omelette", "Eggs", "Beat eggs", "Breakfast", null);
            } else if ("Banana Muffin".compareTo(recipeName) == 0) {
                return new Recipe("cd3ce395-bf3a-414a-a545-c6f07c50999e", "Banana Muffin", "Bananas", "Peel bananas", "Snack", null);
            } else if ("Cheese and Vegetable Frittata".compareTo(recipeName) == 0) {
                return new Recipe("3bd4c8a3-cf55-4ff7-96ea-3398e008f0fc", "Cheese and Vegetable Frittata", "Cheese", "Grate cheese", "Lunch", null);
            } else if ("Chicken and salad wrap".compareTo(recipeName) == 0) {
                return new Recipe("02f5c1d7-56cf-4d45-b120-342e2b72f36b", "Chicken and salad wrap", "Chicken", "Shred chicken", "Lunch", null);
            }
            return new Recipe("", "", "", "", "", null);
    }

    @Override
    public List<String> getRecipeNameById(List<String> recipeIds) {
        return List.of();
    }
}