package com.efs.recipeguideapp.Entity;

public class Ingredient {

    private int ingredientID;
    private String ingredientName;
    private String totalQuantity;
    private String unit;
    private double unitPrice;

    public Ingredient(int ingredientID, String ingredientName, String totalQuantity, String unit, double unitPrice) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.totalQuantity = totalQuantity;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }

    public Ingredient(String ingredientName, String totalQuantity, String unit, double unitPrice) {
        this.ingredientName = ingredientName;
        this.totalQuantity = totalQuantity;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientID=" + ingredientID +
                ", ingredientName='" + ingredientName + '\'' +
                ", totalQuantity='" + totalQuantity + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
