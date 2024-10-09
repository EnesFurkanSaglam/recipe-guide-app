package com.efs.recipeguideapp.DAO;

import com.efs.recipeguideapp.Entity.RecipeIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientDAO {


    private DBConnection dbConnection;

    public RecipeIngredientDAO() {
        this.dbConnection = new DBConnection();
    }


    public List<RecipeIngredient> getAllRecipeIngredients(){

        List<RecipeIngredient> recipeIngredientList =new ArrayList<>();
        String sql = "SELECT * FROM recipeingredients";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int recipeID = resultSet.getInt("RecipeID");
                int ingredientID = resultSet.getInt("IngredientID");
                float ingredientQuantity = resultSet.getFloat("IngredientQuantity");

                RecipeIngredient recipeIngredient = new RecipeIngredient(recipeID,ingredientID,ingredientQuantity);
                recipeIngredientList.add(recipeIngredient);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching recipe ingredients: " + e.getMessage());
        }

        return recipeIngredientList;
    }


    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        String sql = "INSERT INTO recipeingredients (RecipeID,IngredientID,IngredientQuantity) VALUES (?,?,?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, recipeIngredient.getRecipeID());
            preparedStatement.setInt(2, recipeIngredient.getIngredientID());
            preparedStatement.setFloat(3, recipeIngredient.getIngredientQuantity());


            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " recipe ingredient added");

        } catch (SQLException e) {
            System.out.println("Error adding recipe ingredient: " + e.getMessage());
        }
    }


    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void updateIngredientQuantity(RecipeIngredient recipeIngredient){
        String sql = "UPDATE recipeingredients SET IngredientQuantity = ? WHERE RecipeID = ? AND IngredientID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setFloat(1, recipeIngredient.getIngredientQuantity());
            preparedStatement.setInt(2, recipeIngredient.getRecipeID());
            preparedStatement.setInt(3, recipeIngredient.getIngredientID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ingredient Quantity updated successfully.");
            } else {
                System.out.println("No recipe ingredient found with the given RecipeID and IngredientID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating recipe ingredient: " + e.getMessage());
        }

    }

    public void deleteRecipeIngredient(int recipeID,int ingredientID){
        String sql = "DELETE FROM recipeingredients WHERE RecipeID = ? AND IngredientID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, recipeID);
            preparedStatement.setInt(2,ingredientID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Recipe Ingredient deleted successfully.");
            } else {
                System.out.println("No Recipe Ingredient found with the given RecipeID and IngredientID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting Recipe Ingredient: " + e.getMessage());
        }
    }

    public void deleteRecipeIngredientByRecipeID(int recipeID){
        String sql = "DELETE FROM recipeingredients WHERE RecipeID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, recipeID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Recipe Ingredient(s) deleted successfully.");
            } else {
                System.out.println("No Recipe Ingredient(s) found with the given RecipeID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting Recipe Ingredient: " + e.getMessage());
        }
    }

    public void deleteRecipeIngredientByIngredientID(int ingredientID){

        String sql = "DELETE FROM recipeingredients WHERE IngredientID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, ingredientID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Recipe Ingredient(s) deleted successfully.");
            } else {
                System.out.println("No Recipe Ingredient(s) found with the given IngredientID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting Recipe Ingredient: " + e.getMessage());
        }

    }

    public List<RecipeIngredient> getRecipeIngredientsByRecipeID(int myRecipeID) {

        List<RecipeIngredient> recipeIngredientList = new ArrayList<>();
        String sql = "SELECT * FROM recipeingredients WHERE RecipeID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, myRecipeID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int recipeID = resultSet.getInt("RecipeID");
                    int ingredientID = resultSet.getInt("IngredientID");
                    float ingredientQuantity = resultSet.getFloat("IngredientQuantity");

                    RecipeIngredient recipeIngredient = new RecipeIngredient(recipeID, ingredientID, ingredientQuantity);
                    recipeIngredientList.add(recipeIngredient);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching recipe ingredients: " + e.getMessage());
        }

        return recipeIngredientList;
    }

    public List<RecipeIngredient> getRecipeIngredientsByIngredientID(int myIngredientID) {

        List<RecipeIngredient> recipeIngredientList = new ArrayList<>();
        String sql = "SELECT * FROM recipeingredients WHERE IngredientID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, myIngredientID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int recipeID = resultSet.getInt("RecipeID");
                    int ingredientID = resultSet.getInt("IngredientID");
                    float ingredientQuantity = resultSet.getFloat("IngredientQuantity");

                    RecipeIngredient recipeIngredient = new RecipeIngredient(recipeID, ingredientID, ingredientQuantity);
                    recipeIngredientList.add(recipeIngredient);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching recipe ingredients: " + e.getMessage());
        }

        return recipeIngredientList;
    }



}
