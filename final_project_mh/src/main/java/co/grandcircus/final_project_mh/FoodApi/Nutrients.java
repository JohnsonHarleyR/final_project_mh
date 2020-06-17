package co.grandcircus.final_project_mh.FoodApi;

import java.util.List;

public class Nutrients {
	private List<Foods> foods;

	public List<Foods> getFoods() {
		return foods;
	}

	public void setFoods(List<Foods> foods) {
		this.foods = foods;
	}

	@Override
	public String toString() {
		return "Nutrients [foods=" + foods + "]";
	}

	
	
}
