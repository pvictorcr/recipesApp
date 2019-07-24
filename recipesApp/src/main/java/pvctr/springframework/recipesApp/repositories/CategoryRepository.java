package pvctr.springframework.recipesApp.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pvctr.springframework.recipesApp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);
}
