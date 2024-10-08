package com.efs.recipeguideapp;


import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Ingredient;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Entity.RecipeIngredient;
import com.efs.recipeguideapp.Service.IngredientService;
import com.efs.recipeguideapp.Service.RecipeIngredientService;
import com.efs.recipeguideapp.Service.RecipeService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        RecipeDAO recipeDAO = new RecipeDAO();
        IngredientDAO ingredientDAO = new IngredientDAO();
        RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

        RecipeService recipeService = new RecipeService(recipeDAO,recipeIngredientDAO);
        IngredientService ingredientService = new IngredientService(ingredientDAO,recipeIngredientDAO);
        RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO);


        //RecipeIngredient recipeIngredient = new RecipeIngredient(3,6, 2.5F);
        //recipeIngredientService.addRecipeIngredient(recipeIngredient);

        //recipeIngredientService.deleteRecipeIngredient(3,3);

        //recipeService.deleteRecipe(1);

        //System.out.println(recipeIngredientService.getAllRecipeIngredients());



//        RecipeDAO recipeDAO = new RecipeDAO();
//        RecipeService recipeService = new RecipeService(recipeDAO);
//
//        IngredientDAO ingredientDAO = new IngredientDAO();
//        IngredientService ingredientService = new IngredientService(ingredientDAO);


//       ---------List Ingredient---------
//        List<Ingredient> ingredients = ingredientService.getAllIngredient();
//
//        for (Ingredient a : ingredients){
//            System.out.println(a.getIngredientID()+a.getIngredientName());
//        }



//        ---------Add Ingredient---------
//        Ingredient ingredient = new Ingredient("rer","as","as",43);
//        ingredientService.addIngredient(ingredient);


//      ---------Update Ingredient---------
//        for (Ingredient a: ingredients){
//            if (a.getIngredientID() == 7){
//                Ingredient ingredient1 = new Ingredient(a.getIngredientID(),"asdasd","asdads","asdas",12);
//                ingredientService.updateIngredient(ingredient1);
//            }
//
//        }



//         ---------List Recipe---------
//        List<Recipe> recipes = recipeService.getAllRecipes();
//
//        for (Recipe a : recipes){
//            System.out.println(a.getRecipeID()+a.getRecipeName());
//        }

//        -----Add Recipe--------
//        Recipe newRecipe = new Recipe("hmmmmmmmmmmm", "Dinner", 30, "Boil pasta, add sauce.");
//        recipeService.addRecipe(newRecipe);




//         -------Update Recipe------------
//        for (Recipe a : recipes){
//            if (a.getRecipeID() == 13){
//                Recipe recipe = new Recipe(a.getRecipeID(),"qqqqqqqqqqqqqqqqqqqqqqqqqqqq","asdas",12,"fafas");
//                recipeService.updateRecipe(recipe);
//            }
//        }



//         launch();
    }
}