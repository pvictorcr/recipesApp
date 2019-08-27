package pvctr.springframework.recipesApp.services;

import java.util.Set;

import pvctr.springframework.recipesApp.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();
}
