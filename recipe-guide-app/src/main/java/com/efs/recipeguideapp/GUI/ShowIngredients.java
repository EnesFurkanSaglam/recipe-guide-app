package com.efs.recipeguideapp.GUI;

import com.efs.recipeguideapp.DAO.IngredientDAO;
import com.efs.recipeguideapp.DAO.RecipeDAO;
import com.efs.recipeguideapp.DAO.RecipeIngredientDAO;
import com.efs.recipeguideapp.Entity.Ingredient;
import com.efs.recipeguideapp.Entity.Recipe;
import com.efs.recipeguideapp.Service.IngredientService;
import com.efs.recipeguideapp.Service.RecipeIngredientService;
import com.efs.recipeguideapp.Service.RecipeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ShowIngredients {

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<Ingredient> listViewIngredient;

    @FXML
    Button backButton;

    private List<Ingredient> ingredientList;


    @FXML
    private void goBack() {
        Scene currentScene = backButton.getScene();
        currentScene.setRoot(mainPage.getPreviousRoot());
    }

    private Parent previousRoot;

    public Parent getPreviousRoot() {
        return previousRoot;
    }

    private ShowIngredients showIngredients;

    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO, recipeIngredientDAO, ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO, recipeIngredientDAO, recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO, recipeDAO, ingredientDAO);

    private MainPage mainPage;

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setIngredient() {

        ingredientList = ingredientService.getAllIngredient();


        searchTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterIngredients(newValue);
            }
        });


        updateListView(ingredientList);
    }


    private void filterIngredients(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {

            updateListView(ingredientList);
        } else {

            List<Ingredient> filteredList = ingredientList.stream()
                    .filter(ingredient -> ingredient.getIngredientName().toLowerCase().contains(searchTerm.toLowerCase()))
                    .toList();
            updateListView(filteredList);
        }
    }


    private void updateListView(List<Ingredient> ingredientsToShow) {
        listViewIngredient.getItems().clear();
        listViewIngredient.getItems().addAll(ingredientsToShow);

        listViewIngredient.setCellFactory(lv -> new ListCell<Ingredient>() {
            @Override
            protected void updateItem(Ingredient ingredient, boolean empty) {
                super.updateItem(ingredient, empty);

                if (empty || ingredient == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label label = new Label(ingredient.getIngredientName() + "    -    " + ingredient.getTotalQuantity() + " " + ingredient.getUnit() + "    -    " + ingredient.getUnitPrice() + " " + ingredient.getUnit() + "/TL");
                    label.setAlignment(Pos.CENTER);
                    StackPane stackPane = new StackPane(label);
                    stackPane.setAlignment(Pos.CENTER);
                    setGraphic(stackPane);
                }
            }
        });

        listViewIngredient.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Ingredient selectedIngredient = listViewIngredient.getSelectionModel().getSelectedItem();

                if (selectedIngredient != null) {
                    showIngredientDetail(selectedIngredient);
                }
            }
        });
    }

    private void showIngredientDetail(Ingredient ingredient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/ingredient-detail-view.fxml"));
            Parent root = loader.load();

            previousRoot = backButton.getScene().getRoot();

            IngredientDetail controller = loader.getController();
            controller.setIngredient(ingredient);

            Scene currentScene = listViewIngredient.getScene();
            currentScene.setRoot(root);

            controller.setMainPage(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addIngredient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/add-ingredient-view.fxml"));
            Parent root = loader.load();

            previousRoot = backButton.getScene().getRoot();

            AddIngredient controller = loader.getController();
            controller.setShowIngredients(this);

            Scene currentScene = listViewIngredient.getScene();
            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
