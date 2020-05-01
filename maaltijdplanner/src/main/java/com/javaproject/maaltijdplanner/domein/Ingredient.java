package com.javaproject.maaltijdplanner.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String naam;
    private String name;
    private String naam2;
    private String measureUnit;
    private double amount;
    private double energy_kJ;
    private double energy_kcal;
    private double protein_g;
    private boolean highProtein;

    public void ingredientHighProtein(){
        if(this.protein_g > 20) {
            this.highProtein = true;
        }
        else{
            this.highProtein = false;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNaam2() {
        return naam2;
    }

    public void setNaam2(String naam2) {
        this.naam2 = naam2;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getEnergy_kJ() {
        return energy_kJ;
    }

    public void setEnergy_kJ(double energy_kJ) {
        this.energy_kJ = energy_kJ;
    }

    public double getEnergy_kcal() {
        return energy_kcal;
    }

    public void setEnergy_kcal(double energy_kcal) {
        this.energy_kcal = energy_kcal;
    }

    public double getProtein_g() {
        return protein_g;
    }

    public void setProtein_g(double protein_g) {
        this.protein_g = protein_g;
    }

    public boolean isHighProtein() {
        return highProtein;
    }

    public void setHighProtein(boolean highProtein) {
        this.highProtein = highProtein;
    }
}
