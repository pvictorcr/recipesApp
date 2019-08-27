package pvctr.springframework.recipesApp.services;

import lombok.extern.slf4j.Slf4j;
import pvctr.springframework.recipesApp.commands.IngredientCommand;
import pvctr.springframework.recipesApp.converters.IngredientToIngredientCommand;
import pvctr.springframework.recipesApp.domain.Ingredient;
import pvctr.springframework.recipesApp.domain.Recipe;
import pvctr.springframework.recipesApp.repositories.RecipeRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 6/28/17.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

	@Override
	public Set<Ingredient> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingredient findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingredient save(Ingredient object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Ingredient object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
}
