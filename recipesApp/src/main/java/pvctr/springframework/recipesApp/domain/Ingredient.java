package pvctr.springframework.recipesApp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;
	
	@ManyToOne
	private Recipe recipe;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMesure;
	
	public Ingredient() {
	}
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMesure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMesure = unitOfMesure;
    }
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMesure, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unitOfMesure = unitOfMesure;
        this.recipe = recipe;
    }
}
