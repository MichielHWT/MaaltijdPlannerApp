package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Image;
import com.javaproject.maaltijdplanner.domein.Ingredient;
import com.javaproject.maaltijdplanner.domein.Recipe;
import com.javaproject.maaltijdplanner.wrapper.RecipeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeService {
    @Autowired
    RecipeRepository rr;
    @Autowired
    IngredientRepository ir;
    @Autowired
    ImageRepository imr;

    public Iterable<Recipe> getRecipeTable(){
        return rr.findAll();
    }

    public void createNewRecipe(String ingredientsIdsAsString, Recipe recipe){
         String[] ingredientsIdArrayAsString = ingredientsIdsAsString.split("_");
        for (int i = 0; i < ingredientsIdArrayAsString.length; ++i){
            String ingredientIdAsString = ingredientsIdArrayAsString[i];
            long ingredientIdLong = Long.parseLong(ingredientIdAsString);
            Optional<Ingredient> ingredientOptional = ir.findById(ingredientIdLong);
            if(ingredientOptional.isPresent()){
                Ingredient ingredient = ingredientOptional.get();
                recipe.addToIngredientsList(ingredient);
            }
            else{
                System.out.println("Ingredient with this Id does not exist. Recipe will not include this Ingredient.");
            }
        }
        rr.save(recipe);
    }

    public void addNewRecipe(RecipeWrapper recipeWrapper){
        Recipe recipe = new Recipe();
        recipe.setName(recipeWrapper.getName());
        recipe.setDescription(recipeWrapper.getDescription());
        Iterable<Image> allImages = imr.findAll();
        for (Image image : allImages){
            if(image.getFileName().equals(recipeWrapper.getImageName())){
                recipe.setImageFile(image);
                break;
            }
        }
        Iterable<Ingredient> allIngredients = ir.findAll();
        for (Ingredient ingredient : allIngredients){
            if(ingredient.getName().equals(recipeWrapper.getName())){
                recipe.addToIngredientsList(ingredient);
            }
        }
        for (double amount : recipeWrapper.getAmountList()){
            recipe.addToAmountList(amount);
        }
        rr.save(recipe);
    }


    public double calcSumEnergy_kcal(String recipeName){
        double sumEnergy_kcal = 0.0;
        Iterable<Recipe> allRecipes = rr.findAll();
        for (Recipe recipe : allRecipes){
            if(recipe.getName().equals(recipeName)) {
                List<Ingredient> ingredientList = recipe.getIngredientsList();
                for (Ingredient ingredient : ingredientList){
                    int index = ingredientList.indexOf(ingredient);
                    sumEnergy_kcal+= ingredient.getEnergy_kcal()*recipe.getAmountListItem(index);
                }
            }
        }
        return sumEnergy_kcal;
    }

    public double calcSumProtein_g(String recipeName){
        double sumProtein_g = 0.0;
        Iterable<Recipe> allRecipes = rr.findAll();
        for(Recipe recipe : allRecipes){
            if(recipe.getName().equals(recipeName)) {
                List<Ingredient> ingredientList = recipe.getIngredientsList();
                for (Ingredient ingredient : ingredientList){
                    int index = ingredientList.indexOf(ingredient);
                    sumProtein_g += ingredient.getProtein_g()*recipe.getAmountListItem(index);
                }
            }
        }
        return sumProtein_g;
    }

    public Iterable<Recipe> recipeByName(String searchRecipeName){
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Iterable<Recipe> allRecipes = rr.findAll();
        for (Recipe recipe : allRecipes){
            if((recipe.getName()).toLowerCase().contains(searchRecipeName.toLowerCase())) {
                recipeList.add(recipe);
            }
        }
        return recipeList;
    }

    public Iterable<Recipe> recipeByIngredientName(String searchIngredientName){
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Iterable<Recipe> allRecipes = rr.findAll();
        for (Recipe recipe : allRecipes){
            List<Ingredient> ingredientList = recipe.getIngredientsList();
            for (Ingredient ingredient : ingredientList){
                if((ingredient.getNaam()).toLowerCase().contains(searchIngredientName.toLowerCase())){
                    recipeList.add(recipe);
                }
                else if((ingredient.getName()).toLowerCase().contains(searchIngredientName.toLowerCase())){
                    recipeList.add(recipe);
                }
                else if((ingredient.getNaam2()).toLowerCase().contains(searchIngredientName.toLowerCase())){
                    recipeList.add(recipe);
                }
            }
        }
        return recipeList;
    }

}
