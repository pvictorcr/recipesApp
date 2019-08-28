package pvctr.springframework.recipesApp.repositories;

import org.springframework.data.repository.CrudRepository;

import pvctr.springframework.recipesApp.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
