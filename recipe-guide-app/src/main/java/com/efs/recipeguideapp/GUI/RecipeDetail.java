package com.efs.recipeguideapp.GUI;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Ingredient;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Entity.RecipeIngredient;
import com.efs.recipeguideapp.Service.IngredientService;
import com.efs.recipeguideapp.Service.RecipeIngredientService;
import com.efs.recipeguideapp.Service.RecipeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.List;


public class RecipeDetail {

    private Parent previousRoot;

    public Parent getPreviousRoot() {
        return previousRoot;
    }

    @FXML
    Button backButton;

    @FXML
    private void goBack() {

        Scene currentScene = backButton.getScene();
        currentScene.setRoot(mainPage.getPreviousRoot());
    }

    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO, recipeIngredientDAO, ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO, recipeIngredientDAO, recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO, recipeDAO, ingredientDAO);

    private MainPage mainPage;

    Recipe currentRecipe;


    @FXML
    private Label recipeNameLabel;

    @FXML
    private Label recipeCategoryLabel;

    @FXML
    private Label recipePreparationTimeLabel;

    @FXML
    private Label recipeInstructionsLabel;

    @FXML
    private Label instructionsLabel;

    @FXML
    private ListView<RecipeIngredient> recipeIngredientsListView;

    @FXML
    private Label ingredientsLabel;


    @FXML
    private void addIngredient() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/add-recipe-ingredient-view.fxml"));
            Parent root = loader.load();

            previousRoot = backButton.getScene().getRoot();

            AddRecipeIngredient controller = loader.getController();
            controller.setMainPage(this);
            controller.addRecipeIngredient(currentRecipe);

            Scene currentScene = recipeInstructionsLabel.getScene();
            currentScene.setRoot(root);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteRecipe() {
        recipeService.deleteRecipe(currentRecipe.getRecipeID());

        recipeNameLabel.setText("");
        recipeCategoryLabel.setText("");
        recipePreparationTimeLabel.setText("");
        recipeInstructionsLabel.setText("");
        recipeIngredientsListView.getItems().clear();

    }


    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setRecipe(Recipe recipe) {

        currentRecipe = recipe;

        recipeNameLabel.setText(recipe.getRecipeName());
        recipeNameLabel.setFont(new Font("Arial", 40));

        recipeCategoryLabel.setText("Category: " + recipe.getCategory());
        recipeCategoryLabel.setFont(new Font("Arial", 20));

        recipePreparationTimeLabel.setText("Preparation Time: " + String.valueOf(recipe.getPreparationTime()) + " min");
        recipePreparationTimeLabel.setFont(new Font("Arial", 15));

        ingredientsLabel.setFont(new Font("Arial", 20));

        instructionsLabel.setFont(new Font("Arial", 20));
        recipeInstructionsLabel.setText(recipe.getInstructions());


        List<RecipeIngredient> recipeIngredientList = recipeIngredientService.getRecipeIngredientsByRecipeID(recipe.getRecipeID());

        recipeIngredientsListView.getItems().clear();

        recipeIngredientsListView.getItems().addAll(recipeIngredientList);


        recipeIngredientsListView.setCellFactory(lv -> new ListCell<RecipeIngredient>() {
            @Override
            protected void updateItem(RecipeIngredient recipeIngredient, boolean empty) {
                super.updateItem(recipeIngredient, empty);
                if (empty || recipeIngredient == null) {
                    setText(null);
                } else {
                    Ingredient ingredient = ingredientService.getIngredientByID(recipeIngredient.getIngredientID());
                    setText(ingredient.getIngredientName() + "  -  " + recipeIngredient.getIngredientQuantity() + " " + ingredient.getUnit());
                }
            }
        });

        recipeIngredientsListView.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {
                RecipeIngredient selectedRecipeIngredient = recipeIngredientsListView.getSelectionModel().getSelectedItem();

                if (selectedRecipeIngredient != null) {
                    showRecipeIngredientDetails(selectedRecipeIngredient);
                }
            }
        });
    }

    private void showRecipeIngredientDetails(RecipeIngredient recipeIngredient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/recipe-ingredient-detail-view.fxml"));
            Parent root = loader.load();

            previousRoot = backButton.getScene().getRoot();

            RecipeIngredientDetail controller = loader.getController();
            controller.setMainPage(this);
            controller.setRecipeIngredient(recipeIngredient);


            Scene currentScene = recipeIngredientsListView.getScene();
            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void makeRecipe() {

        recipeService.makeRecipe(currentRecipe);

    }


}
