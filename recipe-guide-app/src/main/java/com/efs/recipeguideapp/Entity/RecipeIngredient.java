package com.efs.recipeguideapp.Entity;

public class RecipeIngredient {

    private int recipeID;
    private int ingredientID;
    private float ingredientQuantity;


    public RecipeIngredient(int recipeID, int ingredientID, float ingredientQuantity) {
        this.recipeID = recipeID;
        this.ingredientID = ingredientID;
        this.ingredientQuantity = ingredientQuantity;
    }

    public RecipeIngredient(int ingredientID, float ingredientQuantity) {
        this.ingredientID = ingredientID;
        this.ingredientQuantity = ingredientQuantity;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public float getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(float ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }


    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "recipeID=" + recipeID +
                ", ingredientID=" + ingredientID +
                ", ingredientQuantity=" + ingredientQuantity +
                '}';
    }
}


