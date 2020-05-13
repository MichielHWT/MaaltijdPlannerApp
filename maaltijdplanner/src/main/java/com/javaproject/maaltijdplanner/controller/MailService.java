package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.config.AppConfig;
import com.javaproject.maaltijdplanner.domein.Ingredient;
import com.javaproject.maaltijdplanner.domein.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class MailService {
    @Autowired
    RecipeRepository rr;
    @Autowired
    AppConfig ac;

    //public void sendMailRecipe(String recipeName, String mailAdress) throws Exception{
    public void sendMailRecipe(String recipeName, String mailAdress){
        //Recipe to String
        Iterable<Recipe> allRecipes = rr.findAll();
        String recipeDescription = "";
        for (Recipe recipe : allRecipes){
            if (recipe.getName().equals(recipeName)){
                recipeDescription = recipe.getDescription();
                recipeDescription += "\n";
                recipeDescription += "List of ingredient: \n";
                for (Ingredient ingredient : recipe.getIngredientsList()){

                    for(double amount : recipe.getAmountList()){
                        recipeDescription += amount + "g " + ingredient.getName() + "\n";
                    }
                }
            }
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("MaaltijdPlanner recept " + recipeName);
        mailMessage.setTo(mailAdress);
        mailMessage.setText(recipeDescription);

        ac.getJavaMailSender().send(mailMessage);
    }
}
