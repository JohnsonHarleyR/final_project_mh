package co.grandcircus.final_project_mh.QuoteApi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


//The API works but doesn't have quite what is needed


@Service
public class QuoteService {
	
	private RestTemplate rest = new RestTemplate();
	
	//Get the quote of the day
	public Quote quoteOfTheDay() {
		
		String url = "https://quotes.rest/qod.json";
		
		QuoteResponse response = rest.getForObject(url, QuoteResponse.class);
		
		Quote[] quoteArray = response.getContents().getQuotes();
		
		return quoteArray[0];
		
	}
	
	//Get all categories
	public Categories getCategories() {
		
		String url = "http://quotes.rest/qod/categories.json";
		
		QuoteResponse response = rest.getForObject(url, QuoteResponse.class);
		
		return response.getContents().getCategories();
		
	}
	
	
	

}
