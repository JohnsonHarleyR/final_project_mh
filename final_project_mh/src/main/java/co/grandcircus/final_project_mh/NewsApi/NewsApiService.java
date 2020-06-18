package co.grandcircus.final_project_mh.NewsApi;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NewsApiService {

	private RestTemplate rest = new RestTemplate();
	@Value("${news.api.key}")
	private String apiKey;
	private String excludedDomains ="abc.net.au/news,aljazeera.com,arstechnica.com,mashable.com";
	private String language = "en";
	private List<Article> getTopNews() {
		String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=9f36072691b04780b857f211a9279dad";
		NewsApiResponse response = rest.getForObject(url, NewsApiResponse.class, apiKey);
		List<Article> list = response.getArticles();
		
		return list;
		
	}
	public Article getAArticleByKeyword(String keyword) {
		
		String url = "https://newsapi.org/v2/everything?q=" + keyword + "&apiKey=" + apiKey
				+ "&excludeDomains=" + excludedDomains + "&language=" + language ;
		
		NewsApiResponse response = rest.getForObject(url, NewsApiResponse.class, apiKey, keyword);
		
		List<Article> list = response.getArticles();
		
		Random rand = new Random();
		
	    int length = list.size();
		
	    int articleNumber = rand.nextInt(length);
	    
	    Article article = list.get(articleNumber);
		
		
		
		
		return article;
		
	}
}
