package com.javaproject.maaltijdplanner.api;

import com.javaproject.maaltijdplanner.controller.IngredientRepository;
import com.javaproject.maaltijdplanner.controller.IngredientService;
import com.javaproject.maaltijdplanner.domein.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class IngredientEndpoint {
    @Autowired
    IngredientService is;
    IngredientRepository ir;

    @GetMapping("/ingredientTable")
    public Iterable<Ingredient> getIngredientTable(){
        System.out.println("Return ingredient table");
        //is.uitproberen();
        return is.ingredientTable();
    }

    @GetMapping("/getIngredientById/{ingredientId}")
    public Ingredient getIngredientById(@PathVariable String ingredientId){
        System.out.println("Return Ingredient object");
        long ingredientIdLong = Long.parseLong(ingredientId);
        return is.ingredientById(ingredientIdLong);
    }

    @GetMapping("/getIngredientByName/{searchIngredientName}")
    public Iterable<Ingredient> getIngredientByName(@PathVariable String searchIngredientName){
        return is.ingredientByName(searchIngredientName);
    }

    @PostMapping("/addIngredient")
    public void addIngredient(@RequestBody Ingredient ingredient){
        System.out.println("Add Ingredient to database");
        is.addIngredient(ingredient);
    }

    @DeleteMapping("/deleteIngredient")
    public void deleteIngredient(@RequestBody String ingredientIdString){
        System.out.println("Delete Ingredient from database");
        long ingredientIdLong = Long.parseLong(ingredientIdString);
        is.deleteIngredient(ingredientIdLong);
    }

    @PutMapping("/updateIngredient")
    public void updateIngredient(@RequestBody Ingredient ingredient){
        System.out.println("Update Ingredient in database");
        is.updateIngredient(ingredient);
    }
}
