package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.RecipeIngredient;

import java.util.List;

public class RecipeIngredientService {

    private RecipeIngredientDAO recipeIngredientDAO;
    private RecipeDAO recipeDAO;
    private IngredientDAO ingredientDAO;

    public RecipeIngredientService(RecipeIngredientDAO recipeIngredientDAO, RecipeDAO recipeDAO, IngredientDAO ingredientDAO) {
        this.recipeIngredientDAO = recipeIngredientDAO;
        this.recipeDAO = recipeDAO;
        this.ingredientDAO = ingredientDAO;
    }

    public List<RecipeIngredient> getAllRecipeIngredients() {
        return recipeIngredientDAO.getAllRecipeIngredients();
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredientDAO.addRecipeIngredient(recipeIngredient);
    }

    public void updateIngredientQuantity(RecipeIngredient recipeIngredient) {
        recipeIngredientDAO.updateIngredientQuantity(recipeIngredient);
    }

    public void deleteRecipeIngredient(int recipeID, int ingredientID) {
        recipeIngredientDAO.deleteRecipeIngredient(recipeID, ingredientID);
    }

    public void deleteRecipeIngredientByRecipeID(int recipeID) {
        recipeIngredientDAO.deleteRecipeIngredientByRecipeID(recipeID);
    }

    public void deleteRecipeIngredientByIngredientID(int ingredientID) {
        recipeIngredientDAO.deleteRecipeIngredientByIngredientID(ingredientID);
    }

    public List<RecipeIngredient> getRecipeIngredientsByRecipeID(int myRecipeID) {
        return recipeIngredientDAO.getRecipeIngredientsByRecipeID(myRecipeID);
    }

    public List<RecipeIngredient> getRecipeIngredientsByIngredientID(int myIngredientID) {
        return recipeIngredientDAO.getRecipeIngredientsByIngredientID(myIngredientID);
    }


}
