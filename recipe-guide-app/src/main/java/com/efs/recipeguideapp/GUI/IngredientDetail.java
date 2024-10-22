package com.efs.recipeguideapp.GUI;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Ingredient;
import com.efs.recipeguideapp.Service.IngredientService;
import com.efs.recipeguideapp.Service.RecipeIngredientService;
import com.efs.recipeguideapp.Service.RecipeService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IngredientDetail {

    @FXML
    Button backButton;

    @FXML
    private void goBack() {

        Scene currentScene = backButton.getScene();
        currentScene.setRoot(showIngredients.getPreviousRoot());
    }


    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO, recipeIngredientDAO, ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO, recipeIngredientDAO, recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO, recipeDAO, ingredientDAO);

    private ShowIngredients showIngredients;


    @FXML
    private Label ingredientNameLabel;
    @FXML
    private Label unitLabel;
    @FXML
    private TextField totalQuantityTextField;
    @FXML
    private TextField unitPriceTextField;

    @FXML
    private Label unitPriceLabel;

    Ingredient currentIngredient;

    public void setMainPage(ShowIngredients showIngredients) {
        this.showIngredients = showIngredients;
    }


    public void setIngredient(Ingredient ingredient) {

        currentIngredient = ingredient;

        ingredientNameLabel.setText(ingredient.getIngredientName());
        totalQuantityTextField.setText(ingredient.getTotalQuantity());
        unitLabel.setText(ingredient.getUnit());
        unitPriceTextField.setText(String.valueOf(ingredient.getUnitPrice()));
        unitPriceLabel.setText(ingredient.getUnit() + "/TL");
    }

    @FXML
    private void updateIngredient() {

        Ingredient ingredient = currentIngredient;
        ingredient.setTotalQuantity(totalQuantityTextField.getText());
        ingredient.setUnitPrice(Double.parseDouble(unitPriceTextField.getText()));

        ingredientService.updateIngredient(ingredient);

    }

    @FXML
    private void deleteIngredient() {

        if (currentIngredient != null) {
            ingredientService.deleteIngredient(currentIngredient.getIngredientID());

            ingredientNameLabel.setText("");
            totalQuantityTextField.setText("");
            unitLabel.setText("");
            unitPriceTextField.setText("");

        }
    }
}
