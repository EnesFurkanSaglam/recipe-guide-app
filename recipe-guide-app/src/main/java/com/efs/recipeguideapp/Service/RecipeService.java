package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.Entity.Recipe;
import java.util.List;

public class RecipeService {

    private RecipeDAO recipeDAO;

    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
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

//    public void deleteRecipe(int recipeID) {
//        recipeDAO.deleteRecipe(recipeID);
//    }
}
