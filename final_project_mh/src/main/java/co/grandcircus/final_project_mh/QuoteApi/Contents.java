package co.grandcircus.final_project_mh.QuoteApi;

import java.util.Arrays;

public class Contents {
	
	private Quote[] quotes;
	private Categories categories;

	
	//@return GET quotes
	public Quote[] getQuotes() {
		return quotes;
	}

	
	//@param SET quotes
	public void setQuotes(Quote[] quotes) {
		this.quotes = quotes;
	}
	
	


	
	//@return GET categories
	public Categories getCategories() {
		return categories;
	}


	
	//@param SET categories
	public void setCategories(Categories categories) {
		this.categories = categories;
	}


	@Override
	public String toString() {
		return "Contents [quotes=" + Arrays.toString(quotes) + ", categories=" + categories + "]";
	}
	
	
	

}
