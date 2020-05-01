package com.javaproject.maaltijdplanner.api;

import com.javaproject.maaltijdplanner.controller.IngredientRepository;
import com.javaproject.maaltijdplanner.controller.MailService;
import com.javaproject.maaltijdplanner.controller.RecipeRepository;
import com.javaproject.maaltijdplanner.controller.RecipeService;
import com.javaproject.maaltijdplanner.domein.Ingredient;
import com.javaproject.maaltijdplanner.domein.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.List;

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

    @PostMapping("/recipe/{ingredientsIdsAsString}")
    public void createNewRecipe(@PathVariable String ingredientsIdsAsString, @RequestBody Recipe recipe){
        rs.createNewRecipe(ingredientsIdsAsString, recipe);
    }

    @PostMapping("/sendMailRecipe/{recipeName}")
    public void sendMailRecipe(@PathVariable String recipeName, @RequestBody String mailAdress){
        System.out.println("Send " + recipeName+ " to " + mailAdress);
        ms.sendMailRecipe(recipeName, mailAdress);
    }

    @GetMapping("/getSumEnergy_kcal/{recipeName}")
    public double getSumEnergy_kcal(@PathVariable String recipeName){
        return rs.calcSumEnergy_kcal(recipeName);
    }
}
