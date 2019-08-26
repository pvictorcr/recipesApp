package pvctr.springframework.recipesApp.converters;

import org.springframework.stereotype.Component;

import pvctr.springframework.recipesApp.commands.UnitOfMeasureCommand;
import pvctr.springframework.recipesApp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure {

	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if(source == null) {
			return null;
		}
		
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());
		return uom;
	}
}
