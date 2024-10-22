package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Ingredient;

import java.util.List;

public class IngredientService {

    private IngredientDAO ingredientDAO;
    private RecipeIngredientDAO recipeIngredientDAO;
    private RecipeDAO recipeDAO;


    public IngredientService(IngredientDAO ingredientDAO, RecipeIngredientDAO recipeIngredientDAO, RecipeDAO recipeDAO) {
        this.ingredientDAO = ingredientDAO;
        this.recipeIngredientDAO = recipeIngredientDAO;
        this.recipeDAO = recipeDAO;
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


        recipeIngredientDAO.deleteRecipeIngredientByIngredientID(ingredientID);


        ingredientDAO.deleteIngredient(ingredientID);

    }

    public Ingredient getIngredientByID(int myIngredientID) {
        return ingredientDAO.getIngredientByID(myIngredientID);
    }


}
