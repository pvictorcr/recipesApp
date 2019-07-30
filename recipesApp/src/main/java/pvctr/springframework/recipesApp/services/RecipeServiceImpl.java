package pvctr.springframework.recipesApp.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import pvctr.springframework.recipesApp.domain.Recipe;
import pvctr.springframework.recipesApp.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> findAll() {

		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe save(Recipe object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Recipe object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		return null;
	}

}
