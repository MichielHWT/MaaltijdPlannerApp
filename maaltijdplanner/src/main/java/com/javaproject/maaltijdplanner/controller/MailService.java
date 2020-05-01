package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.config.AppConfig;
import com.javaproject.maaltijdplanner.domein.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Properties;

@Service
@Transactional
public class MailService {
    @Autowired
    RecipeRepository rr;
    @Autowired
    AppConfig ac;

    public void sendMailRecipe(String recipeName, String mailAdress){
        //Recipe to String
        Iterable<Recipe> allRecipes = rr.findAll();
        String recipeDescription = "";
        for (Recipe recipe : allRecipes){
            if (recipe.getName().equals(recipeName)){
                recipeDescription = recipe.getDescription();
            }
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("MaaltijdPlanner recept " + recipeName);
        mailMessage.setTo(mailAdress);
        mailMessage.setText(recipeDescription);

        ac.getJavaMailSender().send(mailMessage);

        /*
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("maaltijdplanner3000@gmail.com");
        mailSender.setPassword("pgeqqjtpqmnidjaf"); //App-password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.debug","true");
        mailSender.send(mailMessage);
        */
    }
}
