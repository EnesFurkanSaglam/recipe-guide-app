package com.efs.recipeguideapp.GUI;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Service.IngredientService;
import com.efs.recipeguideapp.Service.RecipeIngredientService;
import com.efs.recipeguideapp.Service.RecipeService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;


public class RecipeDetail {

    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO,recipeIngredientDAO,ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO,recipeIngredientDAO,recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO,recipeDAO,ingredientDAO);

    private MainPage mainPage;

    @FXML
    private Label recipeNameLabel;

    @FXML
    private Label recipeCategoryLabel;

    @FXML
    private Label recipePreparationTimeLabel;

    @FXML
    private Label recipeInstructionsLabel;

    @FXML
    private Label recipeIngredientsLabel;

//    @FXML
//    private ListView recipeIngredientsListView;

    @FXML
    private Button backButton;

    @FXML
    private Button addDeleteIngredientButton;

    @FXML
    protected void addDeleteIngredient(){}

    @FXML
    protected void goBack() {
        Scene currentScene = backButton.getScene();
        currentScene.setRoot(mainPage.getPreviousRoot());
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setRecipe(Recipe recipe) {
        recipeNameLabel.setText(recipe.getRecipeName());
        recipeNameLabel.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");

        recipeCategoryLabel.setText(recipe.getCategory());
        recipePreparationTimeLabel.setText(String.valueOf(recipe.getPreparationTime()));
        recipeInstructionsLabel.setText(recipe.getInstructions());

        recipeIngredientsLabel.setText(recipeIngredientService.getRecipeIngredientsByRecipeID(recipe.getRecipeID()).toString());
    }
}
