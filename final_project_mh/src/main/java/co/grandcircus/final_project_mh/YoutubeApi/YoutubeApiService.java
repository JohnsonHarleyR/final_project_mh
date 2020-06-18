package co.grandcircus.final_project_mh.YoutubeApi;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YoutubeApiService {
	
	private RestTemplate rest = new RestTemplate();
	@Value("${youtube.api.key}")
	private String apiKey;
	private String part = "snippet";
	private String type = "video";
	public String getRandomVideoIdForVideoDisplay(String q) {
	String url = "https://www.googleapis.com/youtube/v3/search?key" + apiKey +
			"&part=" + part + "&q=" + q + "&type=" + type;
	
	YoutubeApiResponse response = rest.getForObject(url, YoutubeApiResponse.class);
		
	List<Item> items = response.getItems();
	List<String> videoIds = null;
	
	for (Item item: items) {
		String videoId = item.getId().getVideoId();
		videoIds.add(videoId);
	}
	
	Random rand = new Random();
	
    int length = videoIds.size();
	
    int videoIdNumber = rand.nextInt(length);
		
	String selectedId = videoIds.get(videoIdNumber);	
		
		return selectedId;	
	}
}
