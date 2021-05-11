package com.chinjja.taco.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.chinjja.taco.Ingredient;
import com.chinjja.taco.Ingredient.Type;
import com.chinjja.taco.Order;
import com.chinjja.taco.data.IngredientRepository;
import com.chinjja.taco.data.TacoRepository;
import com.chinjja.taco.data.UserRepository;
import com.chinjja.taco.Taco;
import com.chinjja.taco.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	private final IngredientRepository ingredientRepo;
	private final TacoRepository tacoRepo;
	private final UserRepository userRepo;
	
	@Autowired
	public DesignTacoController(
			IngredientRepository ingredientRepo,
			TacoRepository tacoRepo,
			UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
		this.userRepo = userRepo;
	}
	
	@GetMapping
	public String showDesignForm(Model model, Principal principal) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		for(Type type : Ingredient.Type.values()) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user", user);
		
		return "design";
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@PostMapping
	public String procesDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
		if(errors.hasErrors()) {
			return "design";
		}
		
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType() == type).collect(Collectors.toList());
	}
}
