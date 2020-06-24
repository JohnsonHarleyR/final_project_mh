package co.grandcircus.final_project_mh.MentalHealthApi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MHService {

	private RestTemplate rest = new RestTemplate();
	
	String beginUrl = "http://mhanimalsquotesapi-env.eba-cdy5sach.us-east-2.elasticbeanstalk.com/";
	
	// /quotes/all - Get list of all quotes
	
	
	// /animals/all - Get list of all baby animal pics
	// /quotes/tag/{tag} - Get list of quotes by tag
	// /quotes/author/{author} - Get list of quotes by author
	// /animals/type/{type} - Get list of baby animal pics by animal type
	
	
	
	// /quote/random - Get random quote
	public MHQuote getRandomQuote() {
		
		String url = beginUrl + "/quote/random";
		
		MHQuote quote = rest.getForObject(url, MHQuote.class);
		
		return quote;
		
	}
	
	
	// /animal/random - Get random baby animal pic
	public MHAnimal getRandomAnimal() {
		
		String url = beginUrl + "/animal/random";
		
		MHAnimal animal = rest.getForObject(url, MHAnimal.class);
		
		return animal;
		
	}
	
	// /quote/random/{tag} - Get random quote by tag
		public MHQuote getRandomQuoteByTag(String tag) {
			
			String url = beginUrl + "/quote/random{tag}";
			
			MHQuote quote = rest.getForObject(url, MHQuote.class, tag);
			
			return quote;
			
		}
		
	// /animal/random/{type} - Get random baby animal pic by animal type
	// /quote/authors - Get list of quote authors
		
	// /quote/tags - Get list of quote tags
		public String[] getQuoteTags() {
			
			String url = beginUrl + "/quote/tags";
			
			MHTagResponse response = rest.getForObject(url, MHTagResponse.class);
			
			String[] tags = response.getTags();
			
			return tags;
			
		}
		
	// /animal/types - Get list of animal types
public String[] getAnimalTypes() {
			
			String url = beginUrl + "/animal/types";
			
			MHTypeResponse response = rest.getForObject(url, MHTypeResponse.class);
			
			String[] types = response.getTypes();
			
			return types;
			
		}
	
}
