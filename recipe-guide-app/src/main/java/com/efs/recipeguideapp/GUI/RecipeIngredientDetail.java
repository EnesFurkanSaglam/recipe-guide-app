package com.efs.recipeguideapp.GUI;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Entity.RecipeIngredient;
import com.efs.recipeguideapp.Entity.Ingredient;
import com.efs.recipeguideapp.Service.IngredientService;
import com.efs.recipeguideapp.Service.RecipeIngredientService;
import com.efs.recipeguideapp.Service.RecipeService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class RecipeIngredientDetail {


    private RecipeDetail recipeDetail;

    @FXML
    Button backButton;

    @FXML
    private void goBack() {

        Scene currentScene = backButton.getScene();
        currentScene.setRoot(recipeDetail.getPreviousRoot());
    }

    public void setMainPage(RecipeDetail recipeDetail) {
        this.recipeDetail = recipeDetail;
    }


    @FXML
    private Label ingredientNameLabel;

    @FXML
    private TextField ingredientQuantityTextField;

    @FXML
    private Label ingredientUnitLabel;

    private RecipeIngredient currentRecipeIngredient;

    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO, recipeIngredientDAO, ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO, recipeIngredientDAO, recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO, recipeDAO, ingredientDAO);

    public void setRecipeIngredient(RecipeIngredient recipeIngredient) {

        this.currentRecipeIngredient = recipeIngredient;

        Ingredient ingredient = ingredientService.getIngredientByID(recipeIngredient.getIngredientID());

        ingredientNameLabel.setText(ingredient.getIngredientName());
        ingredientQuantityTextField.setText(String.valueOf(recipeIngredient.getIngredientQuantity()));
        ingredientUnitLabel.setText(ingredient.getUnit());
    }

    @FXML
    private void setIngredientQuantity() {

        float newQuantity;
        try {
            newQuantity = Float.parseFloat(ingredientQuantityTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity format");
            return;
        }

        currentRecipeIngredient.setIngredientQuantity(newQuantity);
        recipeIngredientService.updateIngredientQuantity(currentRecipeIngredient);
    }

    @FXML
    private void deleteRecipeIngredient() {

        if (currentRecipeIngredient != null) {

            recipeIngredientService.deleteRecipeIngredient(currentRecipeIngredient.getRecipeID(), currentRecipeIngredient.getIngredientID());

            ingredientNameLabel.setText("");
            ingredientQuantityTextField.setText("");
            ingredientUnitLabel.setText("");

            System.out.println("Ingredient deleted successfully.");
        }
    }


}

