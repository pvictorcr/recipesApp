package pvctr.springframework.recipesApp.services;

import java.util.Set;

import pvctr.springframework.recipesApp.domain.Recipe;

public interface RecipeService extends CrudService<Recipe, Long> {

	Set<Recipe> getRecipes();
}
