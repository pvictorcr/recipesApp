package pvctr.springframework.recipesApp.repositories;

import org.springframework.data.repository.CrudRepository;

import pvctr.springframework.recipesApp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
