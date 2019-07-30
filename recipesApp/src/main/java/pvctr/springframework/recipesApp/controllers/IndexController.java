package pvctr.springframework.recipesApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pvctr.springframework.recipesApp.services.RecipeService;

@Controller
public class IndexController {

	private final RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String getIndexPage(Model model) {

		model.addAttribute("recipes", this.recipeService.findAll());
		System.out.println(">>>>>>>>>"+ this.recipeService.findAll().size());
		return "index";
	}
}
