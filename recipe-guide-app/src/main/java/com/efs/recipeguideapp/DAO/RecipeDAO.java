package com.efs.recipeguideapp.DAO;

import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.GUI.AlertUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    private DBConnection dbConnection;

    public RecipeDAO() {
        dbConnection = new DBConnection();
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        String sql = "SELECT * FROM recipes";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int recipeID = resultSet.getInt("RecipeID");
                String recipeName = resultSet.getString("RecipeName");
                String category = resultSet.getString("Category");
                int preparationTime = resultSet.getInt("PreparationTime");
                String instructions = resultSet.getString("Instructions");

                Recipe recipe = new Recipe(recipeID, recipeName, category, preparationTime, instructions);
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching recipes: " + e.getMessage());
        }

        return recipeList;
    }

    public void addRecipe(Recipe recipe) {
        String sql = "INSERT INTO recipes (RecipeName, Category, PreparationTime, Instructions) VALUES (?,?,?,?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, recipe.getRecipeName());
            preparedStatement.setString(2, recipe.getCategory());
            preparedStatement.setInt(3, recipe.getPreparationTime());
            preparedStatement.setString(4, recipe.getInstructions());

            int rowsAffected = preparedStatement.executeUpdate();
            AlertUtils.showAlert("Info", rowsAffected + " recipe added");
            System.out.println(rowsAffected + " recipe added");

        } catch (SQLException e) {
            System.out.println("Error adding recipe: " + e.getMessage());
        }
    }


    public void updateRecipe(Recipe recipe) {
        String sql = "UPDATE recipes SET RecipeName = ?, Category = ?, PreparationTime = ?, Instructions = ? WHERE RecipeID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, recipe.getRecipeName());
            preparedStatement.setString(2, recipe.getCategory());
            preparedStatement.setInt(3, recipe.getPreparationTime());
            preparedStatement.setString(4, recipe.getInstructions());
            preparedStatement.setInt(5, recipe.getRecipeID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                AlertUtils.showAlert("Info", "Recipe updated successfully.");
                System.out.println("Recipe updated successfully.");
            } else {
                System.out.println("No recipe found with the given RecipeID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating recipe: " + e.getMessage());
        }
    }


    public void deleteRecipe(int recipeID) {
        String sql = "DELETE FROM recipes WHERE RecipeID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, recipeID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Recipe deleted successfully.");
            } else {
                System.out.println("No recipe found with the given RecipeID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting recipe: " + e.getMessage());
        }
    }

    public Recipe getRecipeByID(int myRecipeID) {

        String sql = "SELECT * FROM recipes WHERE RecipeID = ?";
        Recipe recipe = null;

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, myRecipeID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int recipeID = resultSet.getInt("RecipeID");
                    String recipeName = resultSet.getString("RecipeName");
                    String category = resultSet.getString("Category");
                    int preparationTime = resultSet.getInt("PreparationTime");
                    String instructions = resultSet.getString("Instructions");

                    recipe = new Recipe(recipeID, recipeName, category, preparationTime, instructions);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching recipe: " + e.getMessage());
        }

        return recipe;
    }


}
