package com.efs.recipeguideapp;


import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.Entity.Recipe;
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
        RecipeService recipeService = new RecipeService(recipeDAO);


//        -----Add--------
//        Recipe newRecipe = new Recipe("hmmmmmmmmmmm", "Dinner", 30, "Boil pasta, add sauce.");
//        recipeService.addRecipe(newRecipe);


//  ---------List---------
//        List<Recipe> recipes = recipeService.getAllRecipes();
//
//        for (Recipe a : recipes){
//            System.out.println(a.getRecipeID()+a.getRecipeName());
//        }



//         -------Update------------
//        for (Recipe a : recipes){
//            if (a.getRecipeID() == 13){
//                Recipe recipe = new Recipe(a.getRecipeID(),"qqqqqqqqqqqqqqqqqqqqqqqqqqqq","asdas",12,"fafas");
//                recipeService.updateRecipe(recipe);
//            }
//        }



//         launch();
    }
}