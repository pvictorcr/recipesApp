package pvctr.springframework.recipesApp.services;

import java.util.Set;

import pvctr.springframework.recipesApp.commands.RecipeCommand;
import pvctr.springframework.recipesApp.domain.Recipe;

public interface RecipeService extends CrudService<Recipe, Long> {

	Set<Recipe> getRecipes();

    Recipe findById(Long l);
    
    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
    
    void deleteById(Long id);
}
