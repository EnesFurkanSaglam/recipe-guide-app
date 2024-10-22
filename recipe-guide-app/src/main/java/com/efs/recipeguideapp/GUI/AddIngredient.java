package com.efs.recipeguideapp.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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

public class AddIngredient {

    @FXML
    Button backButton;

    @FXML
    private void goBack() {

        Scene currentScene = backButton.getScene();
        currentScene.setRoot(showIngredients.getPreviousRoot());
    }


    private ShowIngredients showIngredients;

    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO, recipeIngredientDAO, ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO, recipeIngredientDAO, recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO, recipeDAO, ingredientDAO);

    @FXML
    private Label ingredientNameLabel;
    @FXML
    private Label ingredientTotalQuantityLabel;
    @FXML
    private Label ingredientUnitLabel;
    @FXML
    private Label ingredientUnitPriceLabel;
    @FXML
    private TextField ingredientTotalQuantityTextField;
    @FXML
    private TextField ingredientUnitPriceTextField;
    @FXML
    private TextField ingredientUnitTextField;
    @FXML
    private TextField ingredientNameTextField;

    @FXML
    private void addIngredient() {

        String name = ingredientNameTextField.getText();
        String totalQuantity = ingredientTotalQuantityTextField.getText();
        String unit = ingredientUnitTextField.getText();
        double unitPrice = Double.parseDouble(ingredientUnitPriceTextField.getText());

        Ingredient ingredient = new Ingredient(name, totalQuantity, unit, unitPrice);

        ingredientService.addIngredient(ingredient);
    }

    public void setShowIngredients(ShowIngredients showIngredients) {
        this.showIngredients = showIngredients;
    }


}
