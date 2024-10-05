package com.efs.recipeguideapp.Database;

import com.efs.recipeguideapp.Classes.Ingredient;
import com.efs.recipeguideapp.Classes.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private DatabaseConnection dbConnection;
    private ArrayList<Recipe> recipeArrayList = new ArrayList<>();

    public Database() {
        dbConnection = new DatabaseConnection();
    }

    public void addRecipe(Recipe recipe) {

        String sql = "INSERT INTO recipes (RecipeName, Category, PreparationTime,Instructions) VALUES (?,?,?,?)";


        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, recipe.getRecipeName());
            preparedStatement.setString(2, recipe.getCategory());
            preparedStatement.setInt(3, recipe.getPreparationTime());
            preparedStatement.setString(4, recipe.getInstructions());


            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " recipe added");

        } catch (SQLException e) {
            System.out.println("query warning" + e.getMessage());
        }



    }

    public void updateRecipe(Recipe recipe) {
        // implementation for updating a recipe
    }

    public void deleteRecipe(int recipeID) {
        // implementation for deleting a recipe
    }

    public List<Recipe> getAllRecipes(){

        recipeArrayList.clear();

        String sql = "SELECT * FROM recipes";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int RecipeID = resultSet.getInt("RecipeID");
                String RecipeName = resultSet.getString("RecipeName");
                String Category = resultSet.getString("Category");
                int PreparationTime = resultSet.getInt("PreparationTime");
                String Instructions = resultSet.getString("Instructions");
                Recipe recipe = new Recipe(RecipeID, RecipeName, Category, PreparationTime, Instructions);

                recipeArrayList.add(recipe);


            }
        } catch (SQLException e) {
            System.out.println("Sorgu hatası: " + e.getMessage());
        }

        return recipeArrayList;
    }

    public void addIngredient(Ingredient ingredient) {
        // implementation for adding an ingredient
    }

    public List<Ingredient> getAllIngredients() {
        // implementation for getting all ingredients
        return null;
    }
}
