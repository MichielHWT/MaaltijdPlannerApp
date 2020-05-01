package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    //1 Via erfrelatie
    //2 via methodenamen naamanalyse reserveerdewoorden icm fieldnamen
    //3 gebruik @Query --> variant van SQL, JPQL (Java persistent query language)

    List<Ingredient> findAllByHighProteinTrue();

    //List<Ingredient>

}
