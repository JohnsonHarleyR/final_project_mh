package co.grandcircus.final_project_mh.AffirmationsApi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AffirmationsService {
	private RestTemplate rest = new RestTemplate();
	
	public String getAffirmation() {
		
		String url = "https://www.affirmations.dev/";
		
		Affirmations response = rest.getForObject(url, Affirmations.class);
		
		String affirmation = response.getAffirmation();
		
		
		return affirmation;
		
	}

}
