package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Ingredient;

import java.util.List;

public class IngredientService {

    private IngredientDAO ingredientDAO;
    private RecipeIngredientDAO recipeIngredientDAO;


    public IngredientService(IngredientDAO ingredientDAO,RecipeIngredientDAO recipeIngredientDAO) {
        this.ingredientDAO = ingredientDAO;
        this.recipeIngredientDAO = recipeIngredientDAO;
    }

    public List<Ingredient> getAllIngredient() {
        return ingredientDAO.getAllIngredient();
    }


    public void addIngredient(Ingredient ingredient) {
        ingredientDAO.addIngredient(ingredient);
    }

    public void updateIngredient(Ingredient ingredient) {
        ingredientDAO.updateIngredient(ingredient);
    }

    public void deleteIngredient(int ingredientID) {

        //firstly we should delete from recipe ingredients table
        recipeIngredientDAO.deleteRecipeIngredientByIngredientID(ingredientID);

        //secondly we delete from recipe table
        ingredientDAO.deleteIngredient(ingredientID);


    }


}
