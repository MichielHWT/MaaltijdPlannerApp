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

    @ElementCollection
    @CollectionTable(name="listOfAmounts")
    private List<Double> amountList = new ArrayList<>();

    @ManyToMany //Links entity, rechts field
    private List<Ingredient> ingredientsList = new ArrayList<>();

    @OneToOne
    private Image imageFile;

    public Image getImageFile() {
        return imageFile;
    }

    public void setImageFile(Image imageFile) {
        this.imageFile = imageFile;
    }

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

    public void addToAmountList(Double amount){
        this.amountList.add(amount);
    }

    public List<Double> getAmountList(){
        return amountList;
    }

    public double getAmountListItem(int index){
        return amountList.get(index);
    }
}
