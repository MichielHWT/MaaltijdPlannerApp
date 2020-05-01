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

    @PutMapping("/updateIngredientProtein_g/{ingredientId}")
    public void updateIngredientProtein_g(@PathVariable String ingredientId, @RequestBody String newFieldValue){
        System.out.println("Update Ingredient Table");
        long ingredientIdLong = Long.parseLong(ingredientId);
        double newProtein_gValue = Double.parseDouble(newFieldValue);
        is.updateIngredientProtein_g(ingredientIdLong, newProtein_gValue);
    }

}
