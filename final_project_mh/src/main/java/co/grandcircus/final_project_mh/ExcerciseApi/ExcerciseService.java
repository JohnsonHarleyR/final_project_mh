package co.grandcircus.final_project_mh.ExcerciseApi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExcerciseService {
	private RestTemplate rest = new RestTemplate();
	@Value("${nutritionx.api.key}")
	private String apiKey;
	@Value("${nutritionx.api.id}")
	private String apiId;
	
	public ExcerciseTracker getTest(String userInput) {
		
		String url = "https://trackapi.nutritionix.com/v2/natural/exercise";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-app-key", apiKey);
		headers.add("x-app-id", apiId);
		headers.add("x-remote-user-id", "0");
		
		Map<String, Object> personJsonObject = new HashMap<>(); 
		personJsonObject.put("query", userInput);
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(personJsonObject,headers);
		
		ExcerciseTracker response = rest.postForObject(url, entity, ExcerciseTracker.class);
		System.out.println(response);
		return response;
	}
	
}
