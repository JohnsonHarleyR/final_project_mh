package co.grandcircus.final_project_mh;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.final_project_mh.DailyQuestions.DailyUserQuestions;
import co.grandcircus.final_project_mh.DailyQuestions.DailyUserQuestionsDao;
import co.grandcircus.final_project_mh.Favorites.AffirmationDao;
import co.grandcircus.final_project_mh.Favorites.ArticleDao;
import co.grandcircus.final_project_mh.Favorites.ExerciseDao;
import co.grandcircus.final_project_mh.Favorites.FavAffirmation;
import co.grandcircus.final_project_mh.Favorites.FavArticle;
import co.grandcircus.final_project_mh.Favorites.FavExercises;
import co.grandcircus.final_project_mh.Favorites.Record;
import co.grandcircus.final_project_mh.Favorites.RecordDao;
import co.grandcircus.final_project_mh.QuoteApi.QuoteService;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;
import co.grandcircus.final_project_mh.UserPreferences.UserPreferences;
import co.grandcircus.final_project_mh.UserPreferences.UserPreferencesDao;


//Figure out why the error messages stay there after logging in
//instead of resetting to default. Fix.


@Controller
public class HomeController {
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserPreferencesDao preferencesRepo;
	
	@Autowired
	private DailyUserQuestionsDao dailyQuestionsRepo;

	

	@RequestMapping("/")
	public String home (Model model) {
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		return "index";
	}
	
	@RequestMapping("/emergency")
	public String emergency(Model model) {
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		              
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		return "emergency";
	}
	
	
	// Takes user to the questionnaire page 
	@RequestMapping("/questionaire")
	public String displayUserQuestionaire(Model model) {
		// getting and setting the user attribute to make the session last as long as possible 
		// and allows the user Id to be passed to the model 
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		
		model.addAttribute("user",user);
		return "user-questionaire";
		
	}
	//Submits the user questionnaire information to the database and redirects to the user info
	@PostMapping("/questionaire")
	public String saveAndDistributeQuestionaireValues(@RequestParam(value="userId") Long userId, @RequestParam(value="mentalHealth[]") String[] mentalHealth,
			@RequestParam(value="musicPreferences[]") String[] musicGenres, @RequestParam(value="userWeight") Integer userWeight,
			@RequestParam(value="userGoalWeight", required = false) Integer userGoalWeight) {
		
		// creating a string to store in the database, separated by ,
		String allMentalIlnesses = "";
		String allMusicGenrePreferences = "";
		
		//creating a new instance of UserPreferences to store in the database for the current user
		UserPreferences userPreferences = new UserPreferences();
		userPreferences.setUserId(userId);
		userPreferences.setUserWeight(userWeight);
		userPreferences.setUserGoalWeight(userGoalWeight);
		
		// since the mental health boxes info comes in as a array the values have to be iterated through
		// and added to a new string to store
		for(String mentalIllness: mentalHealth) {
			
			allMentalIlnesses += mentalIllness + ",";
		}
		userPreferences.setMentalIllnesses(allMentalIlnesses);
		
		for(int i = 0; i < musicGenres.length;i++) {
			if (i == musicGenres.length - 1) 
		{
			allMusicGenrePreferences += musicGenres[i];
		}else 
		{
			allMusicGenrePreferences += musicGenres[i]+ ",";
		}
		}
			
		userPreferences.setMusicGenrePreferences(allMusicGenrePreferences);
		
		preferencesRepo.save(userPreferences);
		
		session.setAttribute("userPreferences", userPreferences);
		return "redirect:/dailycheckin";
		
	}
	
	//daily questionaire controller , may want to put all the questionaire stuff in it's own controller
	// but i'll put it here for now 
	
	@RequestMapping("/dailycheckin")
	public String displayDailyCheckInQuestionaire(Model model){
	
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		
		model.addAttribute("user",user);
		
		
		
		return "daily-user-questionaire";
	}
	@PostMapping("/dailycheckin")
	public String saveDailyResults(@RequestParam (value = "userId") Long userId, @RequestParam (value="feelings") String feeling, 
			@RequestParam (value = "workoutFocus") Integer workoutFocus, @RequestParam (value = "interests") Integer interest) {
				
		DailyUserQuestions dailyQuestion = new DailyUserQuestions();
		
		dailyQuestion.setUserId(userId);
		dailyQuestion.setFeelings(feeling);
		dailyQuestion.setWorkoutFocus(workoutFocus);
		dailyQuestion.setInterest(interest);
		
		dailyQuestionsRepo.save(dailyQuestion);
		session.setAttribute("category", workoutFocus);
		session.setAttribute("keyword", interest);
		session.setAttribute("dailyQuestion", dailyQuestion);
		return "redirect:/";
		
	}
	
	
}
