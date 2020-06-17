package co.grandcircus.final_project_mh;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.final_project_mh.ExcerciseApi.ExcerciseService;
import co.grandcircus.final_project_mh.ExcerciseApi.ExcerciseTracker;
import co.grandcircus.final_project_mh.ExcerciseApi.Exercises;
import co.grandcircus.final_project_mh.Favorites.AffirmationDao;
import co.grandcircus.final_project_mh.Favorites.ExerciseDao;
import co.grandcircus.final_project_mh.Favorites.FavAffirmation;
import co.grandcircus.final_project_mh.Favorites.FavExercises;
import co.grandcircus.final_project_mh.FoodApi.FoodService;
import co.grandcircus.final_project_mh.FoodApi.Foods;
import co.grandcircus.final_project_mh.FoodApi.Nutrients;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;
import co.grandcircus.final_project_mh.WorkoutsApi.ExerciseInfo;
import co.grandcircus.final_project_mh.WorkoutsApi.Results;
import co.grandcircus.final_project_mh.WorkoutsApi.WorkoutService;

@Controller
public class BodyController {

	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private ExcerciseService excerciseService;
	
	@Autowired
	private ExerciseDao exerciseRepo;
	
	@Autowired
	private WorkoutService workoutService;
	
	
	@Autowired
	private FoodService foodService;

	@Autowired
	private HttpSession session;

	@RequestMapping("/body")
	public String body(Model model) {

		boolean loggedIn = Methods.checkLogin(session);
		Integer category = (Integer) session.getAttribute("category");
		
		if (category == null) {
			category = 10;
		}
		
		List<Results> resultList = workoutService.getWorkout(category);
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("loggedin", loggedIn);

		return "body-page";
	}

	@PostMapping("/body")
	public String bodyWithInput(Model model, @RequestParam(required = false) String userInput) {

		boolean loggedIn = Methods.checkLogin(session);

		ExcerciseTracker excerciseTracker = excerciseService.getTest(userInput);

		List<Exercises> exercises = excerciseTracker.getExercises();

		model.addAttribute("exercises", exercises);
		model.addAttribute("loggedin", loggedIn);

		return "body-page";
	}
	@PostMapping("/bodyfood")
	public String foodWithInput(Model model, @RequestParam(required = false) String userInput) {

		boolean loggedIn = Methods.checkLogin(session);

		Nutrients foodTracker = foodService.getTest(userInput);

		List<Foods> food = foodTracker.getFoods();

		Integer category = (Integer) session.getAttribute("category");
		if (category == null) {
			category = 10;
		}
		
		List<Results> resultList = workoutService.getWorkout(category);
		model.addAttribute("food", food);
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("resultList", resultList);

		return "body-page";
	}
	
	@PostMapping("/complete/workout")
	public String completeWorkout(Model model){
		User user = (User)session.getAttribute("user");
		Methods.addWorkoutPoints(user, userRepo);
		return "redirect:/body";
	}
	
	@PostMapping("/save/exercises")
	public String saveExercises(@RequestParam double nf_calories, double duration_min, String name,
			Model model) {
		
		boolean loggedIn = Methods.checkLogin(session);
		
		
		if (!loggedIn) {
			model.addAttribute("loggedin", loggedIn);
		} else {
		
			//Get user
			User user = (User)session.getAttribute("user");
			
		
				
				//Create values for affirmation
				//Date from timestamp
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date date=new Date(timestamp.getTime());
				
				
				FavExercises favorite = 
						new FavExercises(date, nf_calories, duration_min, name, user.getId());
				//Save to favorite
				System.out.println(favorite);
				exerciseRepo.save(favorite);
				Methods.addExercisePoints(user, userRepo);
			}
			
		
		//Find way to let user know if their save was successful
		
		return "redirect:/body";
	}
	
	
	

}
