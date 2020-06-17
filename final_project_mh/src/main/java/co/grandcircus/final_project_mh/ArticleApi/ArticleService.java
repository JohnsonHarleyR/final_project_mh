package co.grandcircus.final_project_mh.ArticleApi;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ArticleService {
		
	private RestTemplate rest = new RestTemplate();

	public Result[] getArticlesByTopic(String topic) {
		
		String url = "https://tools.cdc.gov/api/v2/resources/media?topic={topic}";
		
		TopicResponse response = rest.getForObject(url, TopicResponse.class, topic);
		
		Result[] articleArray = response.getResults();
		
		return articleArray;
		
	}
	
	//By topic id
	//DOES NOT WORK
	public Result[] getArticlesByTopicId(Integer id) {
		
		String url = "https://tools.cdc.gov/api/v2/resources/media?topicids={id}";
		
		TopicResponse response = rest.getForObject(url, TopicResponse.class, id);
		
		Result[] articleArray = response.getResults();
		
		return articleArray;
		
		
	}
	

	
	//tags id
	//https://tools.cdc.gov/api/v2/resources/tags/{tagID}/related
	
	

}
	
