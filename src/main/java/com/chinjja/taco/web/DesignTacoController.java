package com.chinjja.taco.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.PersistentEntityResourceProcessor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.chinjja.taco.Ingredient;
import com.chinjja.taco.Ingredient.Type;
import com.chinjja.taco.Order;
import com.chinjja.taco.data.IngredientRepository;
import com.chinjja.taco.data.TacoRepository;
import com.chinjja.taco.data.UserRepository;
import com.chinjja.taco.web.api.TacoResource;
import com.chinjja.taco.web.api.TacoResourceAssembler;
import com.chinjja.taco.Taco;
import com.chinjja.taco.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RepositoryRestController
public class DesignTacoController {
	private final TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@GetMapping(path="/tacos/recent", produces="application/hal+json")
	public ResponseEntity<CollectionModel<TacoResource>> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		CollectionModel<TacoResource> recentResources = new TacoResourceAssembler().toCollectionModel(tacos);
		recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTacos()).withRel("recents"));
		return ResponseEntity.ok(recentResources);
	}
	
	@Bean
	public RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> tacoProcessor(EntityLinks links) {
		return new RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>() {
			@Override
			public PagedModel<EntityModel<Taco>> process(PagedModel<EntityModel<Taco>> model) {
				model.add(links.linkFor(Taco.class)
						.slash("recent")
						.withRel("recents"));
				return model;
			}
		};
	}
}
