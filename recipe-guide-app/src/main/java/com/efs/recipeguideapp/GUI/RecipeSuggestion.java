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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RecipeSuggestion {

    RecipeDAO recipeDAO = new RecipeDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();

    RecipeService recipeService = new RecipeService(recipeDAO, recipeIngredientDAO, ingredientDAO);
    IngredientService ingredientService = new IngredientService(ingredientDAO, recipeIngredientDAO, recipeDAO);
    RecipeIngredientService recipeIngredientService = new RecipeIngredientService(recipeIngredientDAO, recipeDAO, ingredientDAO);

    @FXML
    ListView<Ingredient> listViewIngredient;

    @FXML
    ListView<Ingredient> listViewSelectedIngredient;

    @FXML
    ListView<RecipeService.RecipeMatch> listViewRecipeMatch;


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

    MainPage mainPage;

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setIngredients() {
        List<Ingredient> ingredientList = ingredientService.getAllIngredient();
        listViewIngredient.getItems().clear();
        listViewIngredient.getItems().addAll(ingredientList);


        setCellFactoryForListView(listViewIngredient);


        setCellFactoryForListView(listViewSelectedIngredient);
    }

    private void setCellFactoryForListView(ListView<Ingredient> listView) {
        listView.setCellFactory(lv -> {
            ListCell<Ingredient> cell = new ListCell<>() {
                @Override
                protected void updateItem(Ingredient ingredient, boolean empty) {
                    super.updateItem(ingredient, empty);
                    if (empty || ingredient == null) {
                        setText(null);
                        setGraphic(null);
                    } else {

                        Label label = new Label(ingredient.getIngredientName());
                        label.setAlignment(Pos.CENTER);

                        StackPane stackPane = new StackPane(label);
                        stackPane.setAlignment(Pos.CENTER);

                        setGraphic(stackPane);
                    }
                }
            };


            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !cell.isEmpty()) {
                    Ingredient selectedIngredient = cell.getItem();


                    if (listViewSelectedIngredient.getItems().contains(selectedIngredient)) {
                        listViewSelectedIngredient.getItems().remove(selectedIngredient);
                    } else {

                        listViewSelectedIngredient.getItems().add(selectedIngredient);
                    }
                }
            });

            return cell;
        });
    }


    @FXML
    private void suggest() {

        List<Ingredient> ingredientList = new ArrayList<>(listViewSelectedIngredient.getItems());


        List<RecipeService.RecipeMatch> suggestedRecipes = recipeService.suggestRecipe(ingredientList);

        listViewRecipeMatch.getItems().clear();
        listViewRecipeMatch.getItems().addAll(suggestedRecipes);

        listViewRecipeMatch.setCellFactory(lv -> new ListCell<RecipeService.RecipeMatch>() {
            @Override
            protected void updateItem(RecipeService.RecipeMatch recipeMatch, boolean empty) {
                super.updateItem(recipeMatch, empty);


                if (empty || recipeMatch == null || recipeMatch.getMatchPercentage() == 0) {
                    setText(null);
                    setGraphic(null);
                } else {

                    double percentage = recipeMatch.getMatchPercentage();
                    DecimalFormat df = new DecimalFormat("#.00");
                    String formattedPercentage = df.format(percentage);


                    Label label = new Label(recipeMatch.getRecipe().getRecipeName() + " " + formattedPercentage + "% Required cost: " + recipeService.RequiredCost(recipeMatch.getRecipe()));
                    label.setAlignment(Pos.CENTER);


                    StackPane stackPane = new StackPane(label);
                    stackPane.setAlignment(Pos.CENTER);

                    setGraphic(stackPane);
                }
            }
        });


    }

}
