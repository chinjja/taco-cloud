package com.chinjja.taco.web.api;

import org.springframework.hateoas.RepresentationModel;

import com.chinjja.taco.Ingredient;
import com.chinjja.taco.Ingredient.Type;

import lombok.Getter;

@Getter
public class IngredientResource extends RepresentationModel<IngredientResource> {
	private String name;
	private Type type;
	
	public IngredientResource(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}
}
