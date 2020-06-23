package co.grandcircus.final_project_mh.MentalHealthApi;

import java.util.Arrays;

public class MHTypeResponse {
	
	private String[] types;

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "MHTypeResponse [types=" + Arrays.toString(types) + "]";
	}
	
	

}
