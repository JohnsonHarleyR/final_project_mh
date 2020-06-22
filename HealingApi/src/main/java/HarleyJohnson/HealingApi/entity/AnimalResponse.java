package HarleyJohnson.HealingApi.entity;

import java.util.List;

public class AnimalResponse {
	
	private List<Animal> animals;

	
	public AnimalResponse() {}
	
	public AnimalResponse(List<Animal> animals) {
		super();
		this.animals = animals;
	}


	//@return GET animals
	public List<Animal> getAnimals() {
		return animals;
	}

	
	//@param SET animals
	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}


	@Override
	public String toString() {
		return "AnimalResponse [animals=" + animals + "]";
	}
	
	

}
