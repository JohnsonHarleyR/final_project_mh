package co.grandcircus.final_project_mh;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.final_project_mh.AffirmationsApi.AffirmationsService;
import co.grandcircus.final_project_mh.Favorites.AffirmationDao;
import co.grandcircus.final_project_mh.Favorites.FavAffirmation;
import co.grandcircus.final_project_mh.MentalHealthApi.MHAnimal;
import co.grandcircus.final_project_mh.MentalHealthApi.MHQuote;
import co.grandcircus.final_project_mh.MentalHealthApi.MHService;
import co.grandcircus.final_project_mh.QuoteApi.Quote;
import co.grandcircus.final_project_mh.QuoteApi.QuoteOfTheDay;
import co.grandcircus.final_project_mh.QuoteApi.QuoteOfTheDayDao;
import co.grandcircus.final_project_mh.QuoteApi.QuoteService;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;
import co.grandcircus.final_project_mh.UserPreferences.UserPreferences;
import co.grandcircus.final_project_mh.YoutubeApi.YoutubeApiService;

@Controller
public class SoulController {
	
	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private MHService mHService;
	
	@Autowired
	private AffirmationDao affirmationRepo;
	
	@Autowired
	private AffirmationsService affirmationsService;
	
	@Autowired
	private QuoteOfTheDayDao quoteRepo;
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private YoutubeApiService youtubeService;
	
	@Autowired
	private HttpSession session;
	
	//Soul page
	@RequestMapping("/soul")
	public String soul(Model model) {
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		User user = (User)session.getAttribute("user");
		//adds progress bar line
		
		if(loggedIn == true) {
		
				String names = Methods.getRank(user, userRepo).getName();
				double maxD = Methods.getRank(user, userRepo).getMaxSoulPoints();
				double minD = Methods.getRank(user, userRepo).getMinSoulPoints();
				double totalD = user.getSoulpoints();

				int percent = (int) ((totalD-minD)/(maxD-minD)*100);
				int total = (int) totalD;
				int max = (int) maxD;
				int min = (int) minD;
				int nextRank = max - total;

				if(percent == 0) {percent = 1;}
				
				model.addAttribute("nextRank", nextRank);
				model.addAttribute("percent",percent);
				model.addAttribute("total", total);
				model.addAttribute("max", max);
				model.addAttribute("min", min);
				model.addAttribute("names", names);
		}
		
		//Get an affirmation
		String affirmation = affirmationsService.getAffirmation();
		
		//Kitten Stuff!
		//Get 2 random numbers
		int wi = (int)(Math.random() * 500 + 200);
		int he = (int)(Math.random() * 500 + 200);
		model.addAttribute("wi", wi);
		model.addAttribute("he", he);
		
		
		//Add random baby animal to page
		MHAnimal baby= mHService.getRandomAnimal();
		model.addAttribute("baby", baby.getUrl());
		System.out.println("Baby animal: " + baby.getUrl());
		
		
		
		String quoteString = "";
		
		//If user is logged in, check to see if it's saved already
		if (loggedIn) {
			
			//Get user  (assigned above with the progress bar)
			//User user = (User)session.getAttribute("user");
			
			//Get list of their favorite Affirmations
			List<FavAffirmation> affirmations =
					affirmationRepo.findByUserId(user.getId());
			
			
			//Loop through favorites to see if it exists already
			boolean exists = false;
			for (FavAffirmation a: affirmations) {
				if (a.getAffirmation().equals(affirmation)) {
					exists = true;
				}
			}
			
			//Tell the jsp whether it exists or not so that it
			//knows whether to show the save button
			model.addAttribute("exists", exists);
			
			UserPreferences userPreferences = null;
			
			if (session.getAttribute("userPreferences") != null) {
				userPreferences = (UserPreferences) session.getAttribute("userPreferences");
			}
			
			String genre = ""; 
			
			if (userPreferences == null) {
				genre = "jazz";
			} else {
				genre = userPreferences.getMusicGenrePreferences();
			}
			
			if (genre.equals("") || genre == null) {
				genre = "jazz";
			}
			
			String q = "";
			String[] genreArray = genre.split(",");
			System.out.println(genreArray);
			
			
			
			for (int i = 0; i < genreArray.length; i++) {
				System.out.println(genreArray[i]);
				if (i == genreArray.length - 1) {
					q += genreArray[i];
				}
				else {
					q += genreArray[i] + "|";
				}
					
			}
			System.out.println(q);
			
			boolean goAhead = true;
			
			try {
				String videoId = youtubeService.getRandomVideoIdForVideoDisplay(q);
				
				model.addAttribute("videoId",videoId);
			} catch (Exception e) {
				goAhead = false;
			}
			model.addAttribute("goahead",goAhead);
			
			
			
			
			
		}
		//If the user isn't logged in, we don't need to worry about
		//"exists".
		
		
		//Now check the day, if there's no quote stored in the database for the day,
		//then get a quote from the API to display.
		//Otherwise, grab it from the database.
		
		//First grab a list of database items
		List<QuoteOfTheDay> allPrevious = quoteRepo.findAll();
		
		//Get a timestamp, convert it to date, see if it matches anything in database
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 Date date = new Date(timestamp.getTime());
		 String sDate = date.toString();
		 String qQuote = "";
		 boolean recorded = false;
		 for (QuoteOfTheDay quote: allPrevious) {
			 if (quote.getDatetime().toString().equals(sDate)) {
				 recorded = true;
				//If it matches anything, add it to the model
				 model.addAttribute("quote", quote);
				 System.out.println(quote.getQuote());
				 System.out.println(quote.getAuthor());
				 
				 if (quote.getAuthor() == null ||
						 quote.getAuthor().equals("")) {
					 quoteString = quote.getQuote();
				 } else {
					 quoteString = quote.getQuote() + " -" + quote.getAuthor();
				 }
				 
				 model.addAttribute("quotestring", quoteString);
				 
			 }
		 }
		
		 QuoteOfTheDay quoteOfDay = new QuoteOfTheDay(date,
				 "You may write me down in history with your bitter, twisted lies. You may trod on me in the very dirt, "
				 + "but still, like dust, I'll rise.", "Maya Angelou");;
		 
		 //if it hasn't matched anything, grab a quote from the API. Store it.
		 if (!recorded) {
			 Quote quote = quoteService.quoteOfTheDay();
			 //set a default in case of error here
			 //It may repeat a quote sometimes, but it's better than a whitepage error
			 qQuote = quote.getQuote(); //not actually sure what this if for anymore lol
			 
			 try {
				 if (quote.getAuthor() != null && !quote.getAuthor().equals("")) {
					 quoteOfDay  = new QuoteOfTheDay(date,
							 quote.getQuote(), quote.getAuthor());
				 } else {
					 quoteOfDay  = new QuoteOfTheDay(date,
							 quote.getQuote(), "");
				 }
				 
				 
			 } catch (Exception e) {
			 }
			 
			System.out.println("Not recorded:" + quoteOfDay.getQuote());
			
			 quoteRepo.save(quoteOfDay);
			 
			 if (quoteOfDay.getAuthor().equals(null) ||
					 quoteOfDay.getAuthor().equals("")) {
				 quoteString = quoteOfDay.getQuote();
			 } else {
				 quoteString = quoteOfDay.getQuote() + " -" + quoteOfDay.getAuthor();
			 }
			 
			 model.addAttribute("quotestring", quoteString);
			 
		 }
		 
		 
	
		 List<FavAffirmation> affirmations =
					affirmationRepo.findByUserId(user.getId());
		 boolean qExists = false;
			for (FavAffirmation a: affirmations) {
				if (a.getAffirmation().equals(quoteString)) {
					qExists = true;
				}
			}
			model.addAttribute("qexists", qExists);

		//Loop through favorites to see if quote exists already
			
			
			
		 
		//MH API quote to put on page
		MHQuote mQuote = mHService.getRandomQuote();
		System.out.println(mQuote.getQuote());
		
		String mQuoteString = mQuote.getQuote() + " -" + mQuote.getAuthor();
		model.addAttribute("mquote", mQuote);
		model.addAttribute("mquotestring", mQuoteString);
	 
		 
		//If user is logged in, check to see if it's saved already
		if (loggedIn) {
			
			//Get user (assigned above with progress bar
			// User user = (User)session.getAttribute("user");
		//Get list of their favorite Affirmations

			
			boolean mExists = false;
			for (FavAffirmation a: affirmations) {
				if (a.getAffirmation().equals(mQuoteString)) {
					mExists = true;
				}
			}
			//Tell the jsp whether it exists or not so that it
			//knows whether to show the save button
			model.addAttribute("mexists", mExists);
		}
		
		
		
		 
		
		model.addAttribute("affirmation", affirmation);
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		return "soul";
	}
	
	
	
	
	
	@PostMapping("/save/affirmation")
	public String saveAffirmation(
			@RequestParam(value = "affirmation") String affirmation,
			Model model) {
		
		boolean loggedIn = Methods.checkLogin(session);
		
		//If not logged in, skip the rest basically
		if (loggedIn) {
		
			//Get user
			User user = (User)session.getAttribute("user");
			
			//Get list of their favorite Affirmations
			List<FavAffirmation> affirmations =
					affirmationRepo.findByUserId(user.getId());
			
			
			//Loop through favorites to see if it exists already
			boolean exists = false;
			for (FavAffirmation a: affirmations) {
				if (a.getAffirmation().equals(affirmation)) {
					exists = true;
				}
			}
			
			
			//Create new favorite - if it doesn't exist
			if (!exists) {
				
				//Create values for affirmation
				//Date from timestamp
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date date=new Date(timestamp.getTime());
				
				
				FavAffirmation favorite = 
						new FavAffirmation(date, affirmation, user.getId());
				//Save to favorite
				affirmationRepo.save(favorite);
				
				//Add points to user
				Methods.addAffirmationPoints(user, userRepo);
				
			}
			
		}
		
		//Find way to let user know if their save was successful
		
		return "redirect:/soul";
	}
	
}
