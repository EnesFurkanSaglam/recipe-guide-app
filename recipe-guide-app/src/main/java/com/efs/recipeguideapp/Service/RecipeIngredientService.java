package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.RecipeIngredient;

import java.util.List;

public class RecipeIngredientService {

    private RecipeIngredientDAO recipeIngredientDAO;

    public RecipeIngredientService(RecipeIngredientDAO recipeIngredientDAO) {
        this.recipeIngredientDAO = recipeIngredientDAO;
    }

    public List<RecipeIngredient> getAllRecipeIngredients(){
        return recipeIngredientDAO.getAllRecipeIngredients();
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        recipeIngredientDAO.addRecipeIngredient(recipeIngredient);
    }

    public void updateIngredientQuantity(RecipeIngredient recipeIngredient){
        recipeIngredientDAO.updateIngredientQuantity(recipeIngredient);
    }

    public void deleteRecipeIngredient(int recipeID,int ingredientID){
        recipeIngredientDAO.deleteRecipeIngredient(recipeID,ingredientID);
    }

    public void deleteRecipeIngredientByRecipeID(int recipeID){
        recipeIngredientDAO.deleteRecipeIngredientByRecipeID(recipeID);
    }

    public void deleteRecipeIngredientByIngredientID(int ingredientID){
        recipeIngredientDAO.deleteRecipeIngredientByIngredientID(ingredientID);
    }

}
