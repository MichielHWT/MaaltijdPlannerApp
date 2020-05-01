package com.javaproject.maaltijdplanner.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;

    @ManyToMany //Links entity, rechts field
    private List<Ingredient> ingredientsList = new ArrayList<>();

    public long getId() {
        return id;
    }

    /*
    public void setId(long id) {
        this.id = id;
    }
    */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addToIngredientsList(Ingredient ingredient){
        this.ingredientsList.add(ingredient);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredientsList(){
        return ingredientsList;
    }
}
