package co.grandcircus.final_project_mh.MentalHealthApi;

import java.util.Arrays;

public class MHAnimalResponse {
	
	private MHAnimal[] animals;

	public MHAnimal[] getAnimals() {
		return animals;
	}

	public void setAnimals(MHAnimal[] animals) {
		this.animals = animals;
	}

	@Override
	public String toString() {
		return "MHAnimalResponse [animals=" + Arrays.toString(animals) + "]";
	}
	
	
	
	

}
