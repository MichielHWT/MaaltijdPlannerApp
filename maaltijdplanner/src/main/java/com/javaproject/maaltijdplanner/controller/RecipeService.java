package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Ingredient;
import com.javaproject.maaltijdplanner.domein.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public double calcSumEnergy_kcal(String recipeName){
        double sumEnergy_kcal = 0.0;
        Iterable<Recipe> allRecipes = rr.findAll();
        for (Recipe recipe : allRecipes){
            if(recipe.getName().equals(recipeName)) {
                List<Ingredient> ingredientList = recipe.getIngredientsList();
                for (Ingredient ingredient : ingredientList){
                    sumEnergy_kcal+= ingredient.getEnergy_kcal();
                }
            }
        }
        return sumEnergy_kcal;
    }
}
