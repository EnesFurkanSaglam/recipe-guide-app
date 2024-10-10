package com.efs.recipeguideapp.GUI;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Service.IngredientService;
import com.efs.recipeguideapp.Service.RecipeIngredientService;
import com.efs.recipeguideapp.Service.RecipeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.util.List;

public class MainPage {

    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO,recipeIngredientDAO,ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO,recipeIngredientDAO,recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO,recipeDAO,ingredientDAO);

    private Parent previousRoot;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private ListView<Recipe> listViewRecipe;

    @FXML
    protected void addRecipe(){}

    @FXML
    protected void showMyIngredients(){}

    @FXML
    private void searchRecipe(KeyEvent e){

        String query = textFieldSearch.getText().toLowerCase();

        List<Recipe> recipeArrayList = recipeService.getAllRecipes();
        ObservableList<Recipe> filteredRecipes = FXCollections.observableArrayList();

        for (Recipe recipe : recipeArrayList) {
            if (recipe.getRecipeName().toLowerCase().contains(query)) {
                filteredRecipes.add(recipe);
            }
        }

        listViewRecipe.setItems(filteredRecipes);

        listViewRecipe.setCellFactory(lv -> new ListCell<Recipe>() {
            @Override
            protected void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);
                if (empty || recipe == null) {
                    setText(null);
                } else {
                    setText("NAME: " + recipe.getRecipeName() + "    -    CATEGORY: " + recipe.getCategory() + "    -    PRICE: " +  recipeService.calculateRecipePrice(recipe.getRecipeID()));
                }
            }
        });

        listViewRecipe.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {
                Recipe selectedRecipe = listViewRecipe.getSelectionModel().getSelectedItem();

                if (selectedRecipe != null) {
                    showRecipeDetails(selectedRecipe);
                }
            }
        });

    }
    private void showRecipeDetails(Recipe recipe) {
        try {

            previousRoot = textFieldSearch.getScene().getRoot();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/recipe-detail-view.fxml"));
            Parent root = loader.load();

            RecipeDetail controller = loader.getController();
            controller.setRecipe(recipe);

            Scene currentScene = textFieldSearch.getScene();
            currentScene.setRoot(root);

            controller.setMainPage(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getPreviousRoot() {
        return previousRoot;
    }


}