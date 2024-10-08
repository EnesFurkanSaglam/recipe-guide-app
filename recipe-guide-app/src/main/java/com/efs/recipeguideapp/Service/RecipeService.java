package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Recipe;

import java.util.List;

public class RecipeService {

    private RecipeDAO recipeDAO;
    private RecipeIngredientDAO recipeIngredientDAO;

    public RecipeService(RecipeDAO recipeDAO,RecipeIngredientDAO recipeIngredientDAO) {
        this.recipeDAO = recipeDAO;
        this.recipeIngredientDAO = recipeIngredientDAO;
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
}
