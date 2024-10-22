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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;


import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPage {

    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO, recipeIngredientDAO, ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO, recipeIngredientDAO, recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO, recipeDAO, ingredientDAO);

    private Parent previousRoot;

    public Parent getPreviousRoot() {
        return previousRoot;
    }


    @FXML
    private TextField textFieldSearch;

    @FXML
    private ListView<Recipe> listViewRecipe;


    @FXML
    private void searchRecipe(KeyEvent e) {

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
            private final StackPane stackPane = new StackPane();
            private final Text text = new Text();

            @Override
            protected void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);
                if (empty || recipe == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    text.setText(recipe.getRecipeName() + "    -    " + recipe.getCategory() + "    -    " + recipeService.calculateRecipePrice(recipe.getRecipeID()) + " TL   -   " + "Required cost : " + recipeService.RequiredCost(recipe) + " TL");
                    stackPane.getChildren().clear();
                    stackPane.getChildren().add(text);

                    if (recipeService.isMakeRecipe(recipe)) {
                        stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    } else {
                        stackPane.setBackground(new Background(new BackgroundFill(Color.rgb(255, 102, 102), CornerRadii.EMPTY, Insets.EMPTY)));
                    }

                    setGraphic(stackPane);
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

    @FXML
    private void addRecipe() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/add-recipe-view.fxml"));
            Parent root = loader.load();

            previousRoot = textFieldSearch.getScene().getRoot();

            AddRecipe controller = loader.getController();
            controller.setMainPage(this);

            Scene currentScene = textFieldSearch.getScene();
            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void showMyIngredients() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/show-ingredients-view.fxml"));
            Parent root = loader.load();


            previousRoot = textFieldSearch.getScene().getRoot();
            ShowIngredients controller = loader.getController();
            controller.setMainPage(this);
            controller.setIngredient();

            Scene currentScene = textFieldSearch.getScene();
            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showRecipeDetails(Recipe recipe) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/recipe-detail-view.fxml"));
            Parent root = loader.load();

            previousRoot = textFieldSearch.getScene().getRoot();


            RecipeDetail controller = loader.getController();
            controller.setRecipe(recipe);

            Scene currentScene = textFieldSearch.getScene();
            currentScene.setRoot(root);

            controller.setMainPage(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void SortByPreparationTime() {
        List<Recipe> recipeArrayList = recipeService.getAllRecipes();


        recipeArrayList.sort((r1, r2) -> Integer.compare(r1.getPreparationTime(), r2.getPreparationTime()));


        ObservableList<Recipe> sortedRecipes = FXCollections.observableArrayList(recipeArrayList);
        listViewRecipe.setItems(sortedRecipes);

        updateListViewCellFactory();
    }

    @FXML
    private void SortByCost() {
        List<Recipe> recipeArrayList = recipeService.getAllRecipes();


        Map<Integer, Double> recipeCostMap = new HashMap<>();
        for (Recipe recipe : recipeArrayList) {
            int recipeID = recipe.getRecipeID();
            double price = recipeService.calculateRecipePrice(recipeID);
            recipeCostMap.put(recipeID, price);
        }


        recipeArrayList.sort(Comparator.comparingDouble(r -> recipeCostMap.get(r.getRecipeID())));


        ObservableList<Recipe> sortedRecipes = FXCollections.observableArrayList(recipeArrayList);
        listViewRecipe.setItems(sortedRecipes);

        updateListViewCellFactory();
    }


    private void updateListViewCellFactory() {
        listViewRecipe.setCellFactory(lv -> new ListCell<Recipe>() {
            private final StackPane stackPane = new StackPane();
            private final Text text = new Text();

            @Override
            protected void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);
                if (empty || recipe == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    text.setText(recipe.getRecipeName() + "    -    " + recipe.getCategory() + "    -    " + recipeService.calculateRecipePrice(recipe.getRecipeID()) + " TL   -   " + "Required cost : " + recipeService.RequiredCost(recipe) + " TL");
                    stackPane.getChildren().clear();
                    stackPane.getChildren().add(text);

                    if (recipeService.isMakeRecipe(recipe)) {
                        stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    } else {
                        stackPane.setBackground(new Background(new BackgroundFill(Color.rgb(255, 102, 102), CornerRadii.EMPTY, Insets.EMPTY)));
                    }

                    setGraphic(stackPane);
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

    @FXML
    private void recipeSuggestion() {
        try {

            String filePath = "./src/main/resources/com/efs/recipeguideapp/recipe-suggestion.wav";
            try {
                File soundFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/efs/recipeguideapp/recipe-suggestion-view.fxml"));
                Parent root = loader.load();

                previousRoot = textFieldSearch.getScene().getRoot();

                RecipeSuggestion controller = loader.getController();
                controller.setIngredients();

                Scene currentScene = textFieldSearch.getScene();
                currentScene.setRoot(root);

                controller.setMainPage(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

