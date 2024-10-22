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


public class AddRecipe {

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

    @FXML
    private Label recipeNameLabel;

    @FXML
    private Label recipeCategoryLabel;

    @FXML
    private Label recipePreparationTimeLabel;

    @FXML
    private Label recipeInstructionsLabel;

    @FXML
    private TextField recipeNameLabelTextField;

    @FXML
    private TextField recipeCategoryTextField;

    @FXML
    private TextField recipePreparationTimeTextField;

    @FXML
    private TextField recipeInstructionsTextField;

    @FXML
    private void addRecipe() {

        String recipeName = recipeNameLabelTextField.getText();
        String recipeCategory = recipeCategoryTextField.getText();
        int recipePreparationTime = Integer.parseInt(recipePreparationTimeTextField.getText());
        String instruction = recipeInstructionsTextField.getText();
        Recipe recipe = new Recipe(recipeName, recipeCategory, recipePreparationTime, instruction);
        recipeService.addRecipe(recipe);
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }


}
