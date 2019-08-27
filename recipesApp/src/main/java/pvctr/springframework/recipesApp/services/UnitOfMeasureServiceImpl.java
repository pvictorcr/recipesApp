package pvctr.springframework.recipesApp.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pvctr.springframework.recipesApp.commands.UnitOfMeasureCommand;
import pvctr.springframework.recipesApp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import pvctr.springframework.recipesApp.repositories.UnitOfMeasureRepository;

@AllArgsConstructor
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {

		return StreamSupport.stream(unitOfMeasureRepository.findAll()
				.spliterator(), false)
				.map(unitOfMeasureToUnitOfMeasureCommand::convert)
				.collect(Collectors.toSet());
	}

}
