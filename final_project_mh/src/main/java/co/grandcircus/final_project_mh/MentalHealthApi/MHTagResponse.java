package co.grandcircus.final_project_mh.MentalHealthApi;

import java.util.Arrays;

public class MHTagResponse {
	
	private String[] tags;

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "MHTagResponse [tags=" + Arrays.toString(tags) + "]";
	}
	
	

}
