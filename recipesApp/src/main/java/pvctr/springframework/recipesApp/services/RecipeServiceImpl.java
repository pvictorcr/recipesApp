package pvctr.springframework.recipesApp.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pvctr.springframework.recipesApp.domain.Recipe;
import pvctr.springframework.recipesApp.repositories.RecipeRepository;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> findAll() {

		log.debug("I'm in a service");
		
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
