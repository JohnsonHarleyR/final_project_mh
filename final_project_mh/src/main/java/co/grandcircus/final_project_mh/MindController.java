package co.grandcircus.final_project_mh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.final_project_mh.ArticleApi.ArticleService;
import co.grandcircus.final_project_mh.Favorites.ArticleDao;
import co.grandcircus.final_project_mh.Favorites.FavArticle;
import co.grandcircus.final_project_mh.NewsApi.Article;
import co.grandcircus.final_project_mh.NewsApi.NewsApiService;
import co.grandcircus.final_project_mh.PickedArticles.PickedArticle;
import co.grandcircus.final_project_mh.PickedArticles.PickedArticles;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

//comment to test github connection

@Controller
public class MindController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private NewsApiService newsService;
	
	@Autowired 
	private ArticleDao articleRepo;
	
	@Autowired
	private UserDao userRepo;
	
	
	// List of possible choices from daily user questionaire
	private String[] questionAnswers = { "top headlines","spirituality","overcoming struggle",
			"funny","mental health", "meditation"};
	
	//List of possible article keywords
	private String[] keywords = {"mental health", "lacking motivation",
			"substance abuse", "anxiety disorders", "self esteem",
			"insomnia", "spirituality", "stress", "trauma", "therapy",
			"autism", "depression", "meditation", "loneliness",
			"suicide prevention", "emotional health", "mental wellbeing",
			"coping with stress", "lifting depression", "overcoming struggle"};
	
	//Articles for body page: healthy eating
	//Articles for soul page: meditation
	
	
	
	//Bailie's API is better.
	//I'd still like to include CDC at the end if I can get something good out of it
	
	//I'm having trouble with getting articles by topic id or multiple words.
	//Maybe I'll still ask the teachers about it
	
	@RequestMapping("/mind")
	public String mindPage(Model model) {
		
		//Get chosen articles
		PickedArticles articleObject = new PickedArticles();
		HashMap<String, PickedArticle> pickedArticles = articleObject.getMap();
		
		User user = (User)session.getAttribute("user");
		
		//Get list of their favorite Articles
		List<FavArticle> articles = new ArrayList<>();
		
		if (user != null) {
			articles = articleRepo.findByUserId(user.getId());
		}
						
		
		boolean loggedIn = Methods.checkLogin(session);
		Integer keywordIndex = (Integer)session.getAttribute("keyword");
		
		//Set a default for when session is empty
		if (keywordIndex == null) {
			keywordIndex = 4;
		}
		
		//Store article keyword - (currently grabs random)
		//String keyword = keywords[Math.abs((int)(Math.random() * keywords.length))];
		String keyword = questionAnswers[keywordIndex];
		
		//test
//		System.out.println(keyword);
//		
//	    int articleNumber = rand.nextInt(length);
//		
//		Result result = results[articleNumber];
//	    
//	    model.addAttribute("result",result);	             
		//System.out.println(result.toString());
	  
	    //NEWS API

		
		Article article1 = null; 
		
		//filters description of article for specific keywords if keyword found it 
		//selects a new article
		do {
		
		article1 = newsService.getAArticleByKeyword(keyword);
	    //if the article returns back as true the ! allows the do/While to break
		}while (!Methods.isArticleOk(article1.getDescription()));
		
		model.addAttribute("article1", article1);
		
		//check if article is saved already
		boolean exists3 = false;
		if (articles != null && !articles.isEmpty()) {
			for (FavArticle b: articles) {
				if (b.getTitle().contentEquals(article1.getTitle())) {
					exists3 = true;
				}
			}
		}
		
		model.addAttribute("exists3", exists3);
		
	
		
	    Article article2 = null;
	    //to make sure 2 different articles come up
	    //2nd it compares the article to a preset list of strings to filter for content
	    do {
	    do {
	    	               
	    	article2 = newsService.getAArticleByKeyword(keyword);
	    } while (article1.getTitle().equals(article2.getTitle()));
	    //if the article returns back as true the ! allows the do/While to break
	    } while (!Methods.isArticleOk(article2.getDescription()));
	        model.addAttribute("article2", article2);
	
	        
	      //check if article is saved already
		boolean exists4 = false;
		if (articles != null && !articles.isEmpty()) {
			for (FavArticle b: articles) {
				if (b.getUrl().contentEquals(article2.getUrl())) {
					exists4 = true;
				}
			}
		}
		System.out.println(exists4);
		model.addAttribute("exists4", exists4);
	    
		//System.out.println(article.toString());
	    //Testing area - datetime stuff
	    
	    //Format datetime
	    //SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy - h:mm a");
	    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	    
	    //Different ways to format:
	    //https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
	    //(Question: Is there a necessary format to store it in database?)
	    
	    //TimeStamp (NOTE: Import timestamp SQL, not security)
	    //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    //model.addAttribute("time", timestamp);
	    
	    //Format timestamp 
	    //(Do I have to use separate ones, or is there a way to skip line in formatter?)
        //String formatted = sdf.format(timestamp);
		//model.addAttribute("ftime", formatted);
		
		
		//Add picked articles to page
		//Add boolean for if it's already saved, to page
	    int n = 1;
		for (String a: pickedArticles.keySet()) {
			model.addAttribute(a, pickedArticles.get(a));
			boolean exists = false;
			for (FavArticle b: articles) {
				if (b.getUrl().equals(pickedArticles.get(a).getUrl())) {
					exists = true;
				}
			}
			String c = "exists" + n;
			model.addAttribute(c, exists);
			n++;
		}
		
		
		
		
		
	    
	    //User stuff
		model.addAttribute("loggedin", loggedIn);

		return "mind";
		
		
		//need to get back from the JSP the user, article name, description, and date
		
		
		
	}
	@PostMapping("/save/article")
	public String saveAffirmation( 
			@RequestParam(value = "title") String title,
			@RequestParam(value = "description") String description,
			@RequestParam(value="url") String url,
			Model model) {
		
		boolean loggedIn = Methods.checkLogin(session);
		
		
		if (!loggedIn) {
			model.addAttribute("loggedin", loggedIn);
		} else {
		
			//Get user
			User user = (User)session.getAttribute("user");
			
			//Get list of their favorite Affirmations
			List<FavArticle> favarticle =
					articleRepo.findByUserId(user.getId());
			
			//errors need to return to refactor 
			//Loop through favorites to see if it exists already
			boolean exists = false;
			for (FavArticle a: favarticle) {
				if (a.getTitle().equals(title)) {
					exists = true;
				}
			}
			
			
			//Create new favorite - if it doesn't exist
			if (!exists) {
				
				//Create values for affirmation
				//Date from timestamp
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date datetime=new Date(timestamp.getTime());
				
				//need to get the variable article
				FavArticle favorite = 
						new FavArticle(datetime, title, 
							description, url, user.getId());
				//Save to favorite
				articleRepo.save(favorite);
				Methods.addArticlePoints(user, userRepo);
			}
			}
			
		
		//Find way to let user know if their save was successful
		return "redirect:/mind";
	}
}

