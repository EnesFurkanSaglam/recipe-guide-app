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
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.util.List;

public class AddRecipeIngredient {


    private RecipeDetail recipeDetail;

    @FXML
    Button backButton;

    @FXML
    private void goBack() {

        Scene currentScene = backButton.getScene();
        currentScene.setRoot(recipeDetail.getPreviousRoot());
    }


    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO, recipeIngredientDAO, ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO, recipeIngredientDAO, recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO, recipeDAO, ingredientDAO);


    @FXML
    private ListView<Ingredient> ingredientListView;

    @FXML
    private Label selectedIngredientLabel;

    @FXML
    private TextField ingredientQuantityTextField;

    @FXML
    private Label unitLabel;

    Ingredient currentIngredient;

    Recipe currentRecipe;

    public void setMainPage(RecipeDetail recipeDetail) {
        this.recipeDetail = recipeDetail;
    }

    public void addRecipeIngredient(Recipe recipe) {

        currentRecipe = recipe;


        List<Ingredient> ingredientList = ingredientService.getAllIngredient();
        ingredientListView.getItems().clear();

        ingredientListView.getItems().addAll(ingredientList);

        ingredientListView.setCellFactory(lv -> new ListCell<Ingredient>() {
            @Override
            protected void updateItem(Ingredient ingredient, boolean empty) {
                super.updateItem(ingredient, empty);
                if (empty || ingredient == null) {
                    setText(null);
                } else {
                    setText(ingredient.getIngredientName());
                }
            }
        });

        ingredientListView.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {
                Ingredient selectedIngredient = ingredientListView.getSelectionModel().getSelectedItem();

                if (selectedIngredient != null) {

                    currentIngredient = selectedIngredient;
                    selectedIngredientLabel.setText(selectedIngredient.getIngredientName());
                    unitLabel.setText(selectedIngredient.getUnit());
                }
            }
        });

    }

    public void addRecipeIngredient() {

        int recipeID = currentRecipe.getRecipeID();
        int ingredientID = currentIngredient.getIngredientID();
        float ingredientQuantity = Float.parseFloat(ingredientQuantityTextField.getText());

        RecipeIngredient recipeIngredient = new RecipeIngredient(recipeID, ingredientID, ingredientQuantity);
        recipeIngredientService.addRecipeIngredient(recipeIngredient);

    }
}
