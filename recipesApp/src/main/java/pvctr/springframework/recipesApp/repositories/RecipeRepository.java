package pvctr.springframework.recipesApp.repositories;

import org.springframework.data.repository.CrudRepository;

import pvctr.springframework.recipesApp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	
}
