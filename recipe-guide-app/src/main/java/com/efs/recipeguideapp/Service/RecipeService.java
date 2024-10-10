package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Ingredient;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Entity.RecipeIngredient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class RecipeService {

    private RecipeDAO recipeDAO;
    private RecipeIngredientDAO recipeIngredientDAO;
    private IngredientDAO ingredientDAO;

    public RecipeService(RecipeDAO recipeDAO,RecipeIngredientDAO recipeIngredientDAO,IngredientDAO ingredientDAO) {
        this.recipeDAO = recipeDAO;
        this.recipeIngredientDAO = recipeIngredientDAO;
        this.ingredientDAO = ingredientDAO;
    }

    public List<Recipe> getAllRecipes() {
        return recipeDAO.getAllRecipes();
    }

    public void addRecipe(Recipe recipe) {
        recipeDAO.addRecipe(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        recipeDAO.updateRecipe(recipe);
    }

    public void deleteRecipe(int recipeID) {

        //firstly we should delete from recipe ingredients table
        recipeIngredientDAO.deleteRecipeIngredientByRecipeID(recipeID);

        //secondly we delete from recipe table
        recipeDAO.deleteRecipe(recipeID);
    }

    public Recipe getRecipeByID(int myRecipeID){
        return recipeDAO.getRecipeByID(myRecipeID);
    }



    public double calculateRecipePrice(int recipeID) {
        double price = 0;

        List<RecipeIngredient> myRecipeIngredients = recipeIngredientDAO.getRecipeIngredientsByRecipeID(recipeID);
        List<Ingredient> ingredientList = ingredientDAO.getAllIngredient();

        for (RecipeIngredient recipeIngredient : myRecipeIngredients) {
            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getIngredientID() == recipeIngredient.getIngredientID()) {
                    price += ingredient.getUnitPrice() * recipeIngredient.getIngredientQuantity();
                    break;
                }
            }
        }

        BigDecimal roundedPrice = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
        return roundedPrice.doubleValue();
    }



}
