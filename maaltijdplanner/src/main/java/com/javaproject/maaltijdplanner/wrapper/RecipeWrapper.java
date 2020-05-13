package com.javaproject.maaltijdplanner.wrapper;

import java.util.ArrayList;

public class RecipeWrapper {
    private String name;
    private String description;
    private ArrayList<Double> amountList;
    private ArrayList<String> ingredientNameList;
    private String imageName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Double> getAmountList() {
        return amountList;
    }

    public void setAmountList(ArrayList<Double> amountList) {
        this.amountList = amountList;
    }

    public ArrayList<String> getIngredientNameList() {
        return ingredientNameList;
    }

    public void setIngredientNameList(ArrayList<String> ingredientNameList) {
        this.ingredientNameList = ingredientNameList;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
