package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.Entity.Ingredient;

import java.util.List;

public class IngredientService {

    private IngredientDAO ingredientDAO;


    public IngredientService(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
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

//    public void deleteIngredient(int ingredientID) {
//        ingredientDAO.deleteIngredient(ingredientID);
//
//    }


}
