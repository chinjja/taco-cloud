package com.chinjja.taco.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.chinjja.taco.Taco;
import com.chinjja.taco.web.DesignTacoController;

public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

	public TacoResourceAssembler() {
		super(DesignTacoController.class, TacoResource.class);
	}

	@Override
	protected TacoResource instantiateModel(Taco entity) {
		return new TacoResource(entity);
	}

	@Override
	public TacoResource toModel(Taco entity) {
		return createModelWithId(entity.getId(), entity);
	}
	
}
