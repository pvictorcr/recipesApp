package pvctr.springframework.recipesApp.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pvctr.springframework.recipesApp.domain.Category;
import pvctr.springframework.recipesApp.domain.UnitOfMeasure;
import pvctr.springframework.recipesApp.repositories.CategoryRepository;
import pvctr.springframework.recipesApp.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String getIndexPage() {
		
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		System.out.println("Cat ID is: " + categoryOptional.get().getId());
		System.out.println("UOM ID is: " + unitOfMeasureOptional.get().getId());
		
		return "index";
	}
}
