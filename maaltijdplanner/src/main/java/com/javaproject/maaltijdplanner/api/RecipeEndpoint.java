package com.javaproject.maaltijdplanner.api;

import com.javaproject.maaltijdplanner.controller.IngredientRepository;
import com.javaproject.maaltijdplanner.controller.MailService;
import com.javaproject.maaltijdplanner.controller.RecipeRepository;
import com.javaproject.maaltijdplanner.controller.RecipeService;
import com.javaproject.maaltijdplanner.domein.Ingredient;
import com.javaproject.maaltijdplanner.domein.Recipe;
import com.javaproject.maaltijdplanner.wrapper.RecipeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeEndpoint {
    @Autowired
    RecipeService rs;
    @Autowired
    RecipeRepository rr;
    @Autowired
    IngredientRepository ir;
    @Autowired
    MailService ms;

    @GetMapping("/getRecipeTable")
    public Iterable<Recipe> getRecipeTable(){
        return rs.getRecipeTable();
    }

/*
    @PostMapping("/recipe/{ingredientsIdsAsString}")
    public void createNewRecipe(@PathVariable String ingredientsIdsAsString, @RequestBody Recipe recipe){
        rs.createNewRecipe(ingredientsIdsAsString, recipe);
    }
*/


    @PostMapping("/sendMailRecipe/{recipeName}")
    public void sendMailRecipe(@PathVariable String recipeName, @RequestBody String mailAdress){
        System.out.println("Send " + recipeName+ " to " + mailAdress);
        try{
            ms.sendMailRecipe(recipeName, mailAdress);
            System.out.println("Succesfully sent mail.");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed sending mail.");
        }finally{
            System.out.println("End of mailservice");
        }
    }

    @PostMapping("/addNewrecipe")
    public void addNewRecipe(@RequestBody RecipeWrapper newRecipe){
        rs.addNewRecipe(newRecipe);
        //System.out.println(newRecipe.toString());
    }

    @GetMapping("/getSumEnergy_kcal/{recipeName}")
    public double getSumEnergy_kcal(@PathVariable String recipeName){
        return rs.calcSumEnergy_kcal(recipeName);
    }

    @GetMapping("/getSumProtein_g/{recipeName}")
    public double getSumProtein_g(@PathVariable String recipeName){
        return rs.calcSumProtein_g(recipeName);
    }

    @GetMapping("/getRecipeByName/{searchRecipeName}")
    public Iterable<Recipe> getRecipeByName(@PathVariable String searchRecipeName){
        return rs.recipeByName(searchRecipeName);
    }

    @GetMapping("/getRecipeByIngredientName/{searchRecipeIngredientName}")
    public Iterable<Recipe> recipeByIngredientName(@PathVariable String searchIngredientName){
        return rs.recipeByIngredientName(searchIngredientName);
    }
}
