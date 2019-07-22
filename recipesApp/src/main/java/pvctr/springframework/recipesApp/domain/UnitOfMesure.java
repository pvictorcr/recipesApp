package pvctr.springframework.recipesApp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnitOfMesure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String unitOfMesure;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUnitOfMesure() {
		return unitOfMesure;
	}
	public void setUnitOfMesure(String unitOfMesure) {
		this.unitOfMesure = unitOfMesure;
	}
	
	
}
