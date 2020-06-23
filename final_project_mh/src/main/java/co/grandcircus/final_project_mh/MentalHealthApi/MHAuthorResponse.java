package co.grandcircus.final_project_mh.MentalHealthApi;

import java.util.Arrays;

public class MHAuthorResponse {
	
	private String[] authors;

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "MHAuthorResponse [authors=" + Arrays.toString(authors) + "]";
	}
	
	

}
