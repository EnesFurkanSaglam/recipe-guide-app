package com.efs.recipeguideapp.DAO;

import com.efs.recipeguideapp.Entity.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {

    private DBConnection dbConnection;

    public IngredientDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    public List<Ingredient> getAllIngredient() {

        List<Ingredient> ingredientList = new ArrayList<>();
        String sql = "SELECT * FROM ingredient";

        try(Connection connection = dbConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()){
                int ingredientID = resultSet.getInt("IngredientID");
                String ingredientName = resultSet.getString("IngredientName");
                String totalQuantity = resultSet.getString("TotalQuantity");
                String unit = resultSet.getString("Unit");
                double unitPrice = resultSet.getDouble("UnitPrice");

                Ingredient ingredient = new Ingredient(ingredientID,ingredientName,totalQuantity,unit,unitPrice);
                ingredientList.add(ingredient);
            }

        }catch (SQLException e) {
            System.out.println("Error fething ingredient : " + e.getMessage());
        }

        return ingredientList;
    }


    public void addIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO ingredient(IngredientName,TotalQuantity,Unit,UnitPrice) VALUES (?,?,?,?)";


        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, ingredient.getIngredientName());
            preparedStatement.setString(2, ingredient.getTotalQuantity());
            preparedStatement.setString(3, ingredient.getUnit());
            preparedStatement.setDouble(4,ingredient.getUnitPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ingredient added");

        } catch (SQLException e) {
            System.out.println("Error adding recipe: " + e.getMessage());
        }
    }

    public void updateIngredient(Ingredient ingredient) {

        String sql = "UPDATE recipes SET IngredientName = ?, TotalQuantity = ?, Unit = ?, UnitPrice = ? WHERE IngredientID = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, ingredient.getIngredientName());
            preparedStatement.setString(2, ingredient.getTotalQuantity());
            preparedStatement.setString(3, ingredient.getUnit());
            preparedStatement.setDouble(4, ingredient.getUnitPrice());
            preparedStatement.setInt(5, ingredient.getIngredientID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ingredient updated successfully.");
            } else {
                System.out.println("No ingredient found with the given IngredientID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating ingredient: " + e.getMessage());
        }
    }




//    public void deleteIngredient(int ingredientID) {
//
//        String sql = "DELETE FROM ingredient WHERE IngredientID = ?";
//
//        try (Connection connection = dbConnection.connect();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setInt(1, ingredientID);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Ingredient deleted successfully.");
//            } else {
//                System.out.println("No ingredient found with the given IngredientID.");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error deleting ingredient: " + e.getMessage());
//        }
//    }

}
