package com.chinjja.taco.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.chinjja.taco.Ingredient;
import com.chinjja.taco.web.DesignTacoController;

public class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {

	public IngredientResourceAssembler() {
		super(DesignTacoController.class, IngredientResource.class);
	}

	@Override
	public IngredientResource toModel(Ingredient entity) {
		return createModelWithId(entity.getId(), entity);
	}

	@Override
	protected IngredientResource instantiateModel(Ingredient entity) {
		return new IngredientResource(entity);
	}

}
