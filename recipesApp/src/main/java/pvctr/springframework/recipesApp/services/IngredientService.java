package pvctr.springframework.recipesApp.services;

import pvctr.springframework.recipesApp.commands.IngredientCommand;
import pvctr.springframework.recipesApp.domain.Ingredient;

public interface IngredientService extends CrudService<Ingredient, Long> {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);
}
