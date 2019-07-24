package pvctr.springframework.recipesApp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pvctr.springframework.recipesApp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String description);
}
