package com.efs.recipeguideapp;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Service.IngredientService;
import com.efs.recipeguideapp.Service.RecipeIngredientService;
import com.efs.recipeguideapp.Service.RecipeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class MainPage {


    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO,recipeIngredientDAO,ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO,recipeIngredientDAO,recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO,recipeDAO,ingredientDAO);


    @FXML
    private TextField textFieldSearch;

    @FXML
    private ListView<String> listViewRecipe;

    @FXML
    protected void addRecipe(){

    }

    @FXML
    protected void showMyIngredients(){

    }



    @FXML
    private void searchRecipe(KeyEvent event){


        String query = textFieldSearch.getText().toLowerCase();

        List<Recipe> recipeArrayList = null;
        recipeArrayList = recipeService.getAllRecipes();

        ObservableList<String> filteredRecipes = FXCollections.observableArrayList();

        for (Recipe recipe : recipeArrayList) {
            if (recipe.getRecipeName().toLowerCase().contains(query)) {
                filteredRecipes.add("NAME: "+recipe.getRecipeName() + "     PREPARATION TIME: " + recipe.getPreparationTime() + "     PRICE: " + recipeService.calculateRecipePrice(recipe.getRecipeID()));
            }
        }

        listViewRecipe.setItems(filteredRecipes);

    }


}