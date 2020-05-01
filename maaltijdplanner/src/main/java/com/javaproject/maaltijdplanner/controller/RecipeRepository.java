package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
