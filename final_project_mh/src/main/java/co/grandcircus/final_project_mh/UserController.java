package co.grandcircus.final_project_mh;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.final_project_mh.Communication.ProfileComments;
import co.grandcircus.final_project_mh.Communication.ProfileCommentsDao;
import co.grandcircus.final_project_mh.Favorites.AffirmationDao;
import co.grandcircus.final_project_mh.Favorites.ArticleDao;
import co.grandcircus.final_project_mh.Favorites.ExerciseDao;
import co.grandcircus.final_project_mh.Favorites.FavAffirmation;
import co.grandcircus.final_project_mh.Favorites.FavArticle;
import co.grandcircus.final_project_mh.Favorites.FavExercises;
import co.grandcircus.final_project_mh.Favorites.Record;
import co.grandcircus.final_project_mh.Favorites.RecordDao;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;
import co.grandcircus.final_project_mh.User.UserMethods;
import co.grandcircus.final_project_mh.UserPreferences.UserPreferences;

@Controller
public class UserController {
	
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
	private ProfileCommentsDao profileCommentsRepo;
	
	private String loginMessage = "Please enter your username or e-mail and password.";
	private String signUpMessage = "Please enter the following information.";
	private String infoMessage = "Here is your user information.";
	private String editMessage = "Edit your user info here.";
	
	
	// PROFILE AND FRIEND PAGES
	
	//Lists all users
	@RequestMapping("/users")
	public String userList(Model model) {
		boolean loggedIn = Methods.checkLogin(session);
		List<User> users = userRepo.findAll();
		
		model.addAttribute("list", users);
		model.addAttribute("loggedin", loggedIn);
		
		return "user-list";
	}
	
	//List all requests
	@RequestMapping("/requests")
	public String requestList(Model model) {
		
		boolean loggedIn = Methods.checkLogin(session);
		User user = (User)session.getAttribute("user");
		
		boolean areRequests = false;
		
		model.addAttribute("loggedin", loggedIn);
		
		if (user != null) {
			//Get list of request ids
			List<String> userIds = new ArrayList<>();
			if (!user.getRequests().equals("")) {
				userIds = UserMethods.idStringToList(user.getRequests());
			}
			//Create list to store users
			List<User> users = new ArrayList<>();
			//turn it into a list of users
			if (!userIds.isEmpty()) {
				for (String id: userIds) {
					if (!id.equals("")) {
						int iId = Integer.parseInt(id);
						long lId = iId;
						Optional<User> optUser = userRepo.findById(lId);
						User tempUser = optUser.get();
						users.add(tempUser);
						
						System.out.println(user.getRequests());
						System.out.println(tempUser.getUsername());
						
						//if list isn't empty, then there are requests
						if (!users.isEmpty()) {
							areRequests = true;
						}
					}
					
				}
			}
			
			if (userIds.size() != 0) {
				areRequests = true;
			} else {
				areRequests = false;
			}
			
			
			if (!users.isEmpty() && users != null) {
				model.addAttribute("list", users);
				
			}
			
			model.addAttribute("request", areRequests);
			return "request-list";
		} else {
			model.addAttribute("request", areRequests);
			return "redirect:/";
		}
		
	}
	
	//User public profile
	@RequestMapping("/profile")
	public String profilePage(
			@RequestParam("id") long id,
			Model model) {
		
		Optional<User> optUser = userRepo.findById(id);
		User profileUser = optUser.get();
		
		//test
		System.out.println(profileUser.getUsername()); //so this part works
		
		boolean loggedIn = Methods.checkLogin(session);
		User loggedUser = (User)session.getAttribute("user");
		
		boolean isFriend = false;
		boolean isRequested = false;
		boolean acceptRequested = false;
		
		//check if profile user is a friend or has a friend request from session user
		if (loggedUser != null) {
			model.addAttribute("user", loggedUser);
			isFriend = UserMethods.checkIfFriends(profileUser, loggedUser);
			isRequested = UserMethods.checkIfRequested(profileUser, loggedUser);
			acceptRequested = UserMethods.checkIfRequested(loggedUser, profileUser);
			System.out.println(isFriend);
		}
		
		//Get list of their comments
				//List<ProfileComments> comments =
					//	profileCommentsRepo.findByUserId(profileUser.getId());
		// This is getting a list of the profile users friends
		Boolean canComment=false;
		List<String> friends = UserMethods.idStringToList(profileUser.getFriends());		
		
		if(loggedUser != null) {
			
		for(String ids: friends) {
		if(loggedUser.getId().toString().equals(ids)) {
			canComment = true;
		}
		}	
		}
		
		List<ProfileComments> comments = new ArrayList<>();
		try {
		comments = profileCommentsRepo.findByProfileId(id);
		}catch(NullPointerException e) {
			
		}
		
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("profileuser", profileUser);
		model.addAttribute("isfriend", isFriend);	
		model.addAttribute("isrequested", isRequested);
		model.addAttribute("acceptrequest", acceptRequested);
		model.addAttribute("canComment",canComment);
		model.addAttribute("comments",comments);
		System.out.println(loggedIn);
		System.out.println(isFriend);
		
		return "public-profile";
	}
	
	@RequestMapping("/delete/comment")
	public String deleteComment(
			@RequestParam(value = "profileuserId") Long profileId,
			@RequestParam(value = "id") Long id,
			Model model) {
		profileCommentsRepo.deleteById(id);
		return "redirect:/profile?id=" + profileId;
	}



	
	@PostMapping("/comment")
	public String saveComment(
			@RequestParam(value= "profileId") Long profileId,
			@RequestParam(value = "comment") String text,
			Model model) {
		
		boolean loggedIn = Methods.checkLogin(session);
		
		
		if (!loggedIn) {
			model.addAttribute("loggedin", loggedIn);
		} else {
		
			//Get user
			User user = (User)session.getAttribute("user");
			
			
			
				
				//Create values for Comment
				//Date from timestamp
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date date=new Date(timestamp.getTime());
				
				
				ProfileComments comment = 
						new ProfileComments(text,user.getId() , date, profileId );
				//Save to favorite
				profileCommentsRepo.save(comment);
				
				Methods.addPoints(5, user, userRepo);
				
			
			
		}
	
			return "redirect:/profile?id=" + profileId;
		
		
		
	}

	
	
	
	//Delete friend
	@RequestMapping("/delete/friend")
	public String deleteFriend(
			@RequestParam("user") long userId,
			@RequestParam("friend") long friendId,
			Model model) {
		
		//Store user
		Optional<User> optUser = userRepo.findById(userId);
		User user = optUser.get();
		
		//Store friend
		Optional<User> optFriend = userRepo.findById(friendId);
		User friend = optFriend.get();
		
		//Delete
		UserMethods.deleteFriend(user, friend, userRepo);
		
		//Make sure session user is updated
		session.setAttribute("user", user);
		
		if (user.getPoints() != 0) {
			Methods.addPoints(-3, user, userRepo);
		}
		
		return "redirect:/profile?id=" + friendId;
	}
	
	//Request friendship
	@RequestMapping("/add/friend")
	public String requestFriendship(
			@RequestParam("user") long userId,
			@RequestParam("friend") long friendId,
			Model model) {
		
		//Store user
		Optional<User> optUser = userRepo.findById(userId);
		User user = optUser.get();
		
		//Store friend
		Optional<User> optFriend = userRepo.findById(friendId);
		User friend = optFriend.get();
		
		//Send friend request
		UserMethods.sendRequest(user, friend, userRepo);
		
		//Make sure session user is updated
		session.setAttribute("user", user);
		
		Methods.addPoints(3, user, userRepo);
		System.out.println("added points");
		
		return "redirect:/profile?id=" + friendId;
	}
	
	//Cancel friend request
	@RequestMapping("/cancel/request")
	public String cancelRequest(
			@RequestParam("user") long userId,
			@RequestParam("friend") long friendId,
			Model model) {
		
		//Store user
		Optional<User> optUser = userRepo.findById(userId);
		User user = optUser.get();
		
		//Store friend
		Optional<User> optFriend = userRepo.findById(friendId);
		User friend = optFriend.get();
		
		//Cancel friend request - order is kinda reversed with requests
		UserMethods.deleteRequest(friend, user, userRepo);
		
		//Make sure session user is updated
		session.setAttribute("user", user);
		
		if (user.getPoints() != 0) {
			Methods.addPoints(-3, user, userRepo);
		}
		
		return "redirect:/profile?id=" + friendId;
	}
	
	//Accept friend request
	@RequestMapping("/accept/request")
	public String acceptRequest(
			@RequestParam("user") long userId,
			@RequestParam("friend") long friendId,
			Model model) {
		
		//Store user
		Optional<User> optUser = userRepo.findById(userId);
		User user = optUser.get();
		
		//Store friend
		Optional<User> optFriend = userRepo.findById(friendId);
		User friend = optFriend.get();
		
		//Cancel friend request - order is kinda reversed with requests
		UserMethods.acceptRequest(user, friend, userRepo);
		
		//Make sure session user is updated
		session.setAttribute("user", user);
		
		Methods.addPoints(3, user, userRepo);
		
		return "redirect:/profile?id=" + friendId;
	}
	
	
	// USER PAGES
	
	//Logged user profile/favorites page
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
		
		User user = (User)session.getAttribute("user");
		
		affirmationRepo.deleteById(id);
		
		if (user.getPoints() != 0) {
			Methods.addPoints(-1, user, userRepo);
		}
		
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
		
		User user = (User)session.getAttribute("user");
		articleRepo.deleteById(id);
		
		if (user.getPoints() != 0) {
			Methods.addPoints(-1, user, userRepo);
		}
		
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
		
		User user = (User)session.getAttribute("user");
		
		exerciseRepo.deleteById(id);
		
		if (user.getPoints() != 0) {
			Methods.addPoints(-1, user, userRepo);
		}
		
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
		
		User user = (User)session.getAttribute("user");
		
		recordRepo.deleteById(id);
		
		if (user.getPoints() != 0) {
			Methods.addPoints(-3, user, userRepo);
		}
		
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
				
				Methods.addPoints(3, user, userRepo);
				
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
			loginMessage = "Please enter your username and password.";
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
			signUpMessage = "Please enter the following information.";
			session.setAttribute("loggedIn", loggedIn);
			//Add user to session
			//Doing this repeatedly to make session last longer
			session.setAttribute("user", user);
			Methods.addPoints(10, user, userRepo);
			
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
			
			editMessage = "Edit your user info here.";
			
			return "redirect:/settings";

		}
		
		
	}

}
