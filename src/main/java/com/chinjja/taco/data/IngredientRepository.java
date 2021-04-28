package com.chinjja.taco.data;

import org.springframework.data.repository.CrudRepository;

import com.chinjja.taco.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
