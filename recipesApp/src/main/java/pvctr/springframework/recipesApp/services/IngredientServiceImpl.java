package pvctr.springframework.recipesApp.services;

import lombok.extern.slf4j.Slf4j;
import pvctr.springframework.recipesApp.commands.IngredientCommand;
import pvctr.springframework.recipesApp.commands.RecipeCommand;
import pvctr.springframework.recipesApp.converters.IngredientCommandToIngredient;
import pvctr.springframework.recipesApp.converters.IngredientToIngredientCommand;
import pvctr.springframework.recipesApp.converters.RecipeToRecipeCommand;
import pvctr.springframework.recipesApp.domain.Ingredient;
import pvctr.springframework.recipesApp.domain.Recipe;
import pvctr.springframework.recipesApp.repositories.IngredientRepository;
import pvctr.springframework.recipesApp.repositories.RecipeRepository;
import pvctr.springframework.recipesApp.repositories.UnitOfMeasureRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 6/28/17.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
    		IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository,
    		UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository,
    		RecipeToRecipeCommand recipeToRecipeCommand) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
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
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
            	Ingredient ingredient = ingredientCommandToIngredient.convert(command);
            	ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);
            
            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
            		.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
            		.findFirst();
            
            if(!savedIngredientOptional.isPresent()) {
            	//not totally safe...
            	savedIngredientOptional = savedRecipe.getIngredients().stream()
            			.filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
            			.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
            			.filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(command.getUnitOfMeasure().getId()))
            			.findFirst();
            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }
    
    @Override
    @Transactional
    public RecipeCommand deleteIngredientCommand(IngredientCommand command) {
        
    	Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //TODO launch error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
            	
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientRepository.delete(ingredientFound);
                recipeOptional.get().getIngredients().remove(ingredientFound);

            } else {
                //add new Ingredient
            	log.error("Ingredient not found for id: " + command.getId() + ", recipeId: " + command.getRecipeId());
            }
        }
        
        return recipeToRecipeCommand.convert(recipeOptional.get());
    }
    
    @Override
	public void deleteById(Long recipeId, Long id) {

    	log.debug("Deleting ingredient: recipeId=" + recipeId + ", id=" + id);
    	
    	Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    	
    	if(recipeOptional.isPresent()) {
    		Recipe recipe = recipeOptional.get();
    		log.debug("recipe found");
    		
    		Optional<Ingredient> ingredientOptional = recipe
    				.getIngredients()
    				.stream()
    				.filter(ingredient -> ingredient.getId().equals(id))
    				.findFirst();
    		
    		if(ingredientOptional.isPresent()) {
    			log.debug("ingredient found");
    			Ingredient ingredientToDelete = ingredientOptional.get();
    			ingredientToDelete.setRecipe(null);
    			recipe.getIngredients().remove(ingredientOptional.get());
    			recipeRepository.save(recipe);
    		}
    		else {
    			log.debug("Recipe Id not found: " + recipeId);
    		}
    	}
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
