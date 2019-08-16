package pvctr.springframework.recipesApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import pvctr.springframework.recipesApp.services.RecipeService;

@Slf4j
@Controller
public class IndexController {

	private final RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String getIndexPage(Model model) {

		log.debug("Getting Index Page");
		
		model.addAttribute("recipes", this.recipeService.findAll());
		return "index";
	}
}
