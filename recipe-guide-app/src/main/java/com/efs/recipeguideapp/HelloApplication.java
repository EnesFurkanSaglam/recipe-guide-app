package com.efs.recipeguideapp;

import com.efs.recipeguideapp.Classes.Recipe;
import com.efs.recipeguideapp.Database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


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

        Database database = new Database();

        for(Recipe a :database.getAllRecipes()){
            System.out.println(a.getRecipeID()+a.getRecipeName());
        }


        Recipe recipe = new Recipe("merhaba","dasda",12,"afaasfas");

        database.addRecipe(recipe);

        for(Recipe a :database.getAllRecipes()){
            System.out.println(a.getRecipeID()+a.getRecipeName());
        }


       // launch();
    }
}