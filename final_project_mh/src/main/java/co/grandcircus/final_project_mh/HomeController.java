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
	private UserDao userRepo;
	
	@Autowired
	private ArticleDao articleRepo;
	
	@Autowired
	private AffirmationDao affirmationRepo;
	
	@Autowired
	private ExerciseDao exerciseRepo;
	
	@Autowired
	private RecordDao recordRepo;
	
	@Autowired
	private UserPreferencesDao preferencesRepo;
	
	@Autowired
	private DailyUserQuestionsDao dailyQuestionsRepo;

	private String loginMessage = "Please enter your username or e-mail and password.";
	private String signUpMessage = "Please enter the following information.";
	private String infoMessage = "Here is your user information.";
	private String editMessage = "Edit your user info here.";
	

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
	
	
	
	// USER PAGES
	
	
	//User profile/favorites page
	@RequestMapping("/user")
	public String userPage(Model model) {
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		//in case the person leaves their computer on this page
		//if the user gets logged out, redirect
		if (!loggedIn)
			return "redirect:/";
		
		User user = (User)session.getAttribute("user");
		
		
		
		//Get list of their favorite Affirmations
		List<FavAffirmation> affirmations =
				affirmationRepo.findByUserId(user.getId());
		//Sort and then reverse its order so newest is at the top
		Collections.sort(affirmations);
		Collections.reverse(affirmations);
		
		//Get list of their records
		List<Record> records =
				recordRepo.findByUserId(user.getId());
		//Sort and then reverse its order so newest is at the top
		Collections.sort(records);
		Collections.reverse(records);
		
		
		//Get list of their favorite Affirmations
		List<FavExercises> exercises =
				exerciseRepo.findByUserId(user.getId());
		//Sort and then reverse its order so newest is at the top
		Collections.sort(exercises);
		Collections.reverse(exercises);
		
		//Get list of their favorite articles
		List<FavArticle> articles =
				articleRepo.findByUserId(user.getId());
		//Sort and then reverse its order so newest is at the top
		Collections.sort(articles);
		Collections.reverse(articles);
		
		
				
		//for the header
		model.addAttribute("loggedin", loggedIn);
		//Add user
		model.addAttribute("user", user);
		
		//Add affirmation list
		model.addAttribute("affirmations", affirmations);
		//Add affirmation list
		model.addAttribute("articles", articles);
		//Add record list
		model.addAttribute("records", records);
		//Add completed exercises list
		model.addAttribute("exercises", exercises);
		
		
		return "user-page";
	}
	
	//Expanded list - can be multiple things, like on pizza lab
	@RequestMapping("/list/affirmations")
	public String list(Model model) {
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		User user = (User)session.getAttribute("user");
		
		//Get list of their favorite Affirmations
		List<FavAffirmation> list =
				affirmationRepo.findByUserId(user.getId());
		//Sort and then reverse its order so newest is at the top
		Collections.sort(list);
		Collections.reverse(list);
				
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		model.addAttribute("list", list);
		
		return "affirmations-list";
	}
	
	
	//Delete affirmation
	//Taking a url allows us to come here from 2 different pages
	@RequestMapping("/delete/affirmation")
	public String deleteAffirmation(
			@RequestParam(value = "url") String url,
			@RequestParam(value = "id") Long id,
			Model model) {
		
		affirmationRepo.deleteById(id);
		
		return "redirect:" + url;
	}
	
	//Expanded list - can be multiple things, like on pizza lab
	@RequestMapping("/list/articles")
	public String articleList(Model model) {
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		User user = (User)session.getAttribute("user");
		
		//Get list of their favorite Affirmations
		List<FavArticle> list =
				articleRepo.findByUserId(user.getId());
		//Sort and then reverse its order so newest is at the top
		Collections.sort(list);
		Collections.reverse(list);
				
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		model.addAttribute("list", list);
		
		return "articles-list";
	}
	
	
	//Delete affirmation
	//Taking a url allows us to come here from 2 different pages
	@RequestMapping("/delete/article")
	public String deleteArticle(
			@RequestParam(value = "url") String url,
			@RequestParam(value = "id") Long id,
			Model model) {
		
		articleRepo.deleteById(id);
		
		return "redirect:" + url;
	}
	
	
	//Expanded list - can be multiple things, like on pizza lab
	@RequestMapping("/list/exercises")
	public String exerciseList(Model model) {
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		User user = (User)session.getAttribute("user");
		
		//Get list of their favorite Affirmations
		List<FavExercises> list =
				exerciseRepo.findByUserId(user.getId());
		//Sort and then reverse its order so newest is at the top
		Collections.sort(list);
		Collections.reverse(list);
				
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		model.addAttribute("list", list);
		
		return "exercises-list";
	}
	
	
	//Delete affirmation
	//Taking a url allows us to come here from 2 different pages
	@RequestMapping("/delete/exercise")
	public String deleteExercise(
			@RequestParam(value = "url") String url,
			@RequestParam(value = "id") Long id,
			Model model) {
		
		exerciseRepo.deleteById(id);
		
		return "redirect:" + url;
	}
	
	
	
	//Expanded list - can be multiple things, like on pizza lab
	@RequestMapping("/list/records")
	public String listRecords(Model model) {
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		User user = (User)session.getAttribute("user");
		
		//Get list of their records
		List<Record> list =
				recordRepo.findByUserId(user.getId());
		//Sort and then reverse its order so newest is at the top
		Collections.sort(list);
		Collections.reverse(list);
				
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		model.addAttribute("list", list);
		
		return "records-list";
	}
	
	
	//Delete record
	//Taking a url allows us to come here from 2 different pages
	@RequestMapping("/delete/record")
	public String deleteRecord(
			@RequestParam(value = "url") String url,
			@RequestParam(value = "id") Long id,
			Model model) {
		
		recordRepo.deleteById(id);
		
		return "redirect:" + url;
	}



	
	@PostMapping("/record")
	public String saveRecord(
			@RequestParam(value = "list") String list,
			@RequestParam(value = "text") String text,
			Model model) {
		
		boolean loggedIn = Methods.checkLogin(session);
		
		
		if (!loggedIn) {
			model.addAttribute("loggedin", loggedIn);
		} else {
		
			//Get user
			User user = (User)session.getAttribute("user");
			
			//Get list of their favorite Affirmations
			List<Record> records =
					recordRepo.findByUserId(user.getId());
			
			
			//Loop through favorites to see if it exists already
			boolean exists = false;
			for (Record r: records) {
				if (r.getText().equals(text)) {
					exists = true;
				}
			}
			
			
			//Create new favorite - if it doesn't exist
			if (!exists) {
				
				//Create values for affirmation
				//Date from timestamp
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date date=new Date(timestamp.getTime());
				
				
				Record favorite = 
						new Record(date, text, user.getId());
				//Save to favorite
				recordRepo.save(favorite);
			}
			
		}
		
		//Find way to let user know if their save was successful
		
		//if list variable is yes, redirect to list page
		if (list.equals("yes")) {
			return "redirect:/list/records";
		} else {
			return "redirect:/user";
		}
		
		
	}

	
	
	// Login page
	@RequestMapping("/login")
	public String login(Model model) {
		
		//Check if user is logged in
		boolean loggedIn = Methods.checkLogin(session);

		// tell nav bar whether user is logged in
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("message", loginMessage);

		return "login";
	}

	// Login submit
	@PostMapping("/login/submit")
	public String submit(@RequestParam("identity") String identity, @RequestParam String password, Model model,
			RedirectAttributes redir // not sure what this does? Pass to another method? useful
	) {

		User user;
		
		//Check if user is logged in, set as variable
		boolean loggedIn = Methods.checkLogin(session);

		// see if user exists
		// find out if it's an email or username
		if (identity.contains("@")) {
			user = userRepo.findByEmail(identity);
		} else {
			user = userRepo.findByUsername(identity);
		}

		// 1. user is found
		// - or -
		// 2. password is incorrect

		if (user == null || !password.contentEquals(user.getPassword())) {
			loginMessage = "Your username, email, or password was incorrect.";
			model.addAttribute("message", loginMessage);
			loggedIn = false;
			return "redirect:/login";
		} else {
			loggedIn = true;
		}

		//Add user to session
		//Doing this repeatedly to make session last longer
		session.setAttribute("user", user);
		
		//set whether user is logged in or not
		session.setAttribute("loggedIn", loggedIn);

		return "redirect:/dailycheckin";
	}

	// Logout
	@RequestMapping("/logout")
	public String logout(RedirectAttributes redir) {

		loginMessage = "Please enter your username and password.";
		signUpMessage = "Please enter the following information.";
		infoMessage = "Here is your user information.";
		editMessage = "Edit your user info here.";

		// removes objects added to session

		session.invalidate();
		session.setAttribute("loggedIn", false);
		session.setAttribute("user", null);
		
		return "redirect:/login";
	}

	// Sign up page
	@RequestMapping("/sign-up")
	public String signUp(Model model) {
		
		//Check if user is logged in, set as variable
		boolean loggedIn = Methods.checkLogin(session);

		// tell nav bar whether user is logged in
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("message", signUpMessage);

		return "sign-up";
	}

	// Login submit
	@PostMapping("sign-up/submit")
	public String signUpSubmit(@RequestParam(value = "username") String username,
			@RequestParam(value = "email") String email, @RequestParam(value = "password1") String password1,
			@RequestParam(value = "password2") String password2, @RequestParam(value = "name") String name,
			Model model) {

		List<User> users = userRepo.findAll();
		
		//Check if user is logged in, set as variable
		boolean loggedIn = Methods.checkLogin(session);

		for (User u : users) {
			if (u.getUsername().equals(username)) {
				signUpMessage = "Username already exists. Please choose another.";
				model.addAttribute("loggedin", loggedIn);
				model.addAttribute("message", signUpMessage);
				return "redirect:/sign-up";
			}
			if (u.getEmail().equals(email)) {
				signUpMessage = "Email already exists. Please choose another.";
				model.addAttribute("loggedin", loggedIn);
				model.addAttribute("message", signUpMessage);
				return "redirect:/sign-up";
			}

		}

		if (!password1.equals(password2)) {
			signUpMessage = "Passwords did not match. Please try again.";
			model.addAttribute("loggedin", loggedIn);
			model.addAttribute("message", signUpMessage);
			return "redirect:/sign-up";
		} else {
			// make it so the email has to match a regex too
			User user = new User(username, email, password1, name);
			userRepo.save(user);
			session.setAttribute("user", user);
			loggedIn = true;
			infoMessage = "Sign up was successful!";
			session.setAttribute("loggedIn", loggedIn);
			//Add user to session
			//Doing this repeatedly to make session last longer
			session.setAttribute("user", user);
			
			return "redirect:/questionaire";

		}
	}

	// Add user page
	@RequestMapping("/add")
	public String addTask(

			Model model) {

		User user = (User) session.getAttribute("user");
		
		//Check if user is logged in, set as variable
		boolean loggedIn = Methods.checkLogin(session);
		

		model.addAttribute("user", user);
		model.addAttribute("loggedin", loggedIn);

		if (loggedIn == true) {
			return "add";
		} else {
			return "redirect:/login";
		}

	}

	// User info page
	@RequestMapping("/settings")
	public String userSettings(Model model) {

		User user = (User) session.getAttribute("user");
		UserPreferences userPreferences = (UserPreferences) session.getAttribute("userPreferences");
		//Check if user is logged in, set as variable
		boolean loggedIn = Methods.checkLogin(session);
		
		String hiddenPass = "";

		// redirect to login if not logged in
		if (user == null) {
			return "redirect:/login";
		}

		for (int i = 0; i < user.getPassword().length(); i++) {
			hiddenPass += "*";
		}

		model.addAttribute("user", user);
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("password", hiddenPass);
		model.addAttribute("message", infoMessage);
		model.addAttribute("userPreferences",userPreferences);
		
		session.setAttribute("loggedIn", loggedIn);
		
		//Add user to session
		//Doing this repeatedly to make session last longer
		session.setAttribute("user", user);

		return "user-info";
	}

	// Edit user page
	@RequestMapping("/user/edit")
	public String editUser(Model model) {

		User user = (User) session.getAttribute("user");
		
		//Check if user is logged in, set as variable
		boolean loggedIn = Methods.checkLogin(session);

		// redirect to login if not logged in
		if (user == null) {
			loggedIn = false;
			session.setAttribute("loggedIn", loggedIn);
			return "redirect:/login";
		} else {
			model.addAttribute("user", user);
			model.addAttribute("loggedin", loggedIn);
			model.addAttribute("message", editMessage);
			
			session.setAttribute("loggedIn", loggedIn);
			
			//Add user to session
			//Doing this repeatedly to make session last longer
			session.setAttribute("user", user);

			return "edit-user";
		}
	}

	// Submit changes to user
	@PostMapping("/user/edit/submit")
	public String edit(@RequestParam(value = "userid") Long userId, @RequestParam(value = "username") String username,
			@RequestParam(value = "email") String email, @RequestParam(value = "password1") String password1,
			@RequestParam(value = "password2") String password2, @RequestParam(value = "name") String name,
			Model model) {
		
		//Check if user is logged in, set as variable
		boolean loggedIn = Methods.checkLogin(session);

		List<User> users = userRepo.findAll();
		User us = (User) session.getAttribute("user");
		User user = userRepo.findByUsername(us.getUsername()); // unnecessary steps - fix

		for (User u : users) {
			if (u.getUsername().equals(username) && u.getId() != user.getId()) {
				editMessage = "New username is unavailable. Please choose another.";
				model.addAttribute("user", user);
				model.addAttribute("loggedin", loggedIn);
				model.addAttribute("message", infoMessage);
				
				return "redirect:/user/edit";
			}
		}
		
		session.setAttribute("loggedIn", loggedIn);
		
		//Add user to session
		//Doing this repeatedly to make session last longer
		session.setAttribute("user", user);

		if (!password1.equals(password2)) {
			editMessage = "Passwords did not match. Please try again.";
			model.addAttribute("user", user);
			model.addAttribute("loggedin", loggedIn);
			model.addAttribute("message", editMessage);
			return "redirect:/user/edit";
		} else {
			// make it so the email has to match a regex too
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password1);
			user.setName(name);
			userRepo.save(user);
			session.setAttribute("user", user);
			loggedIn = true;
			infoMessage = "Information was successfully edited.";
			
			session.setAttribute("loggedIn", loggedIn);
			
			return "redirect:/settings";

		}
		
		
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
		session.setAttribute("catagory", workoutFocus);
		session.setAttribute("keyword", interest);
		session.setAttribute("dailyQuestion", dailyQuestion);
		return "redirect:/";
		
	}
	
	
}
