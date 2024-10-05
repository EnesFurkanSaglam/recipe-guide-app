package com.efs.recipeguideapp.Classes;

import java.util.ArrayList;

public class Recipe {


    private int recipeID;
    private String recipeName;
    private String category;
    private int preparationTime;
    private String instructions;


    ArrayList<Recipe> recipeArrayList = new ArrayList<>();


    public Recipe(int recipeID, String recipeName, String category, int preparationTime, String instructions) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.category = category;
        this.preparationTime = preparationTime;
        this.instructions = instructions;
    }

    public Recipe(String recipeName, String category, int preparationTime, String instructions) {
        this.recipeName = recipeName;
        this.category = category;
        this.preparationTime = preparationTime;
        this.instructions = instructions;
    }

    public Recipe() {}





    public void add() { /* implementation */ }
    public void update() { /* implementation */ }
    public void delete() { /* implementation */ }
    public void showDetails() { /* implementation */ }


    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
