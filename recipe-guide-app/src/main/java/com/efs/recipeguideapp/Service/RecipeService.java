package com.efs.recipeguideapp.Service;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Ingredient;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Entity.RecipeIngredient;
import com.efs.recipeguideapp.GUI.AlertUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RecipeService {

    private RecipeDAO recipeDAO;
    private RecipeIngredientDAO recipeIngredientDAO;
    private IngredientDAO ingredientDAO;

    public RecipeService(RecipeDAO recipeDAO, RecipeIngredientDAO recipeIngredientDAO, IngredientDAO ingredientDAO) {
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


        recipeIngredientDAO.deleteRecipeIngredientByRecipeID(recipeID);

        recipeDAO.deleteRecipe(recipeID);
    }

    public Recipe getRecipeByID(int myRecipeID) {
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

    public boolean isMakeRecipe(Recipe recipe) {

        List<RecipeIngredient> recipeIngredientList = recipeIngredientDAO.getRecipeIngredientsByRecipeID(recipe.getRecipeID());

        for (RecipeIngredient recipeIngredient : recipeIngredientList) {

            Ingredient ingredient = ingredientDAO.getIngredientByID(recipeIngredient.getIngredientID());

            float totalQuantity;
            try {
                totalQuantity = Float.parseFloat(ingredient.getTotalQuantity());
            } catch (NumberFormatException e) {

                return false;
            }

            if (recipeIngredient.getIngredientQuantity() > totalQuantity) {
                return false;
            }
        }

        return true;
    }


    public void makeRecipe(Recipe recipe) {

        if (isMakeRecipe(recipe)) {

            List<RecipeIngredient> recipeIngredientList = recipeIngredientDAO.getRecipeIngredientsByRecipeID(recipe.getRecipeID());

            for (RecipeIngredient recipeIngredient : recipeIngredientList) {

                Ingredient ingredient = ingredientDAO.getIngredientByID(recipeIngredient.getIngredientID());


                float totalQuantity;
                try {
                    totalQuantity = Float.parseFloat(ingredient.getTotalQuantity());
                } catch (NumberFormatException e) {

                    System.out.println("Invalid quantity format for ingredient: " + ingredient.getIngredientName());
                    return;
                }


                float newQuantity = totalQuantity - recipeIngredient.getIngredientQuantity();
                ingredient.setTotalQuantity(String.valueOf(newQuantity));
                ingredientDAO.updateIngredient(ingredient);
            }

            AlertUtils.showAlert("Info", "The recipe is prepared.");
            System.out.println("Recipe made successfully! Ingredients have been updated.");
        } else {
            AlertUtils.showError("Not enough ingredients to make the recipe.");
            System.out.println("Not enough ingredients to make the recipe.");
        }
    }

    public float RequiredCost(Recipe recipe) {

        float totalCost = 0;


        List<RecipeIngredient> recipeIngredientList = recipeIngredientDAO.getRecipeIngredientsByRecipeID(recipe.getRecipeID());


        for (RecipeIngredient recipeIngredient : recipeIngredientList) {

            Ingredient ingredient = ingredientDAO.getIngredientByID(recipeIngredient.getIngredientID());

            float totalQuantity;
            try {
                totalQuantity = Float.parseFloat(ingredient.getTotalQuantity());
            } catch (NumberFormatException e) {

                totalQuantity = 0;
            }


            if (recipeIngredient.getIngredientQuantity() > totalQuantity) {
                float missingQuantity = recipeIngredient.getIngredientQuantity() - totalQuantity;
                float ingredientCost = (float) ingredient.getUnitPrice();


                totalCost += missingQuantity * ingredientCost;
            }
        }

        return totalCost;
    }

    public List<RecipeMatch> suggestRecipe(List<Ingredient> selectedIngredients) {
        List<Recipe> allRecipes = recipeDAO.getAllRecipes();
        List<RecipeMatch> matchedRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            List<RecipeIngredient> recipeIngredients = recipeIngredientDAO.getRecipeIngredientsByRecipeID(recipe.getRecipeID());


            if (recipeIngredients.isEmpty()) {

                matchedRecipes.add(new RecipeMatch(recipe, 0));
                continue;
            }

            int matchingIngredients = 0;
            for (RecipeIngredient recipeIngredient : recipeIngredients) {
                for (Ingredient selectedIngredient : selectedIngredients) {
                    if (recipeIngredient.getIngredientID() == selectedIngredient.getIngredientID()) {
                        matchingIngredients++;
                        break;
                    }
                }
            }


            double matchPercentage = (double) matchingIngredients / recipeIngredients.size() * 100;


            matchedRecipes.add(new RecipeMatch(recipe, matchPercentage));


        }

        matchedRecipes.sort((r1, r2) -> Double.compare(r2.getMatchPercentage(), r1.getMatchPercentage()));

        return matchedRecipes;
    }


    public class RecipeMatch {

        private Recipe recipe;
        private double matchPercentage;

        public RecipeMatch(Recipe recipe, double matchPercentage) {
            this.recipe = recipe;
            this.matchPercentage = matchPercentage;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        public double getMatchPercentage() {
            return matchPercentage;
        }
    }


}


