package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientService{
    @Autowired
    IngredientRepository ir;

        //Return the Ingredient objects from the SQL database to FrontEnd
        public Iterable<Ingredient> ingredientTable(){
            return ir.findAll();
        }

        //Return the Ingredient object with given Id from SQL database to FrontEnd
        //Returns null if Id does not exist in database
        public Ingredient ingredientById(long ingredientId){
            Optional<Ingredient> ingredientOptional = ir.findById(ingredientId);
            Ingredient ingredient;
            if(ingredientOptional.isPresent()){
                ingredient = ingredientOptional.get();
            }
            else{
                ingredient = null;
            }
            return ingredient;
        }

        public Iterable<Ingredient> ingredientByName(String searchIngredientName){
            ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
            Iterable<Ingredient> allIngredients = ir.findAll();
            for (Ingredient ingredient : allIngredients){
                if((ingredient.getNaam()).toLowerCase().contains(searchIngredientName.toLowerCase())){
                    ingredientList.add(ingredient);
                }
                else if((ingredient.getName()).toLowerCase().contains(searchIngredientName.toLowerCase())){
                    ingredientList.add(ingredient);
                }
                else if((ingredient.getNaam2()).toLowerCase().contains(searchIngredientName.toLowerCase())){
                    ingredientList.add(ingredient);
                }
            }
            return ingredientList;
        }

        //Get new ingredient from FrontEnd to add to SQL database
        public void addIngredient(Ingredient ingredient){
            ir.save(ingredient);
        }

        //Delete ingredient in SQL database based on Id (must be long)
        public void deleteIngredient(long ingredientId){
            ir.deleteById(ingredientId);
        }

        //Update ingredient in SQL database
        public void updateIngredient(Ingredient ingredient){
            ir.save(ingredient);
        }
}
