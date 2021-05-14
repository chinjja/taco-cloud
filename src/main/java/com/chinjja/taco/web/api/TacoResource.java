package com.chinjja.taco.web.api;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.chinjja.taco.Taco;

import lombok.Getter;

@Getter
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends RepresentationModel<TacoResource> {
	private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();
	
	private final String name;
	private final Date createdAt;
	private final Iterable<IngredientResource> ingredients;
	
	public TacoResource(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
	}
}
