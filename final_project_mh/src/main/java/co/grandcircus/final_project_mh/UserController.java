package co.grandcircus.final_project_mh;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import co.grandcircus.final_project_mh.Gamification.Achievements;
import co.grandcircus.final_project_mh.Gamification.AchievementsRepo;
import co.grandcircus.final_project_mh.User.Conversation;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;
import co.grandcircus.final_project_mh.User.UserMessage;
import co.grandcircus.final_project_mh.User.UserMessageDao;
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
	private UserMessageDao userMessageRepo;
	
	@Autowired
	private ProfileCommentsDao profileCommentsRepo;
	
	@Autowired private AchievementsRepo achievementsRepo;
	
	private String loginMessage = "Please enter your username or e-mail and password.";
	private String signUpMessage = "Please enter the following information.";
	private String infoMessage = "Here is your user information.";
	private String editMessage = "Edit your user info here.";
	
	
	
	//Choose user avatar
	@RequestMapping("/avatar")
	public String avatars(Model model) {
		boolean loggedIn = Methods.checkLogin(session);
		User user = (User)session.getAttribute("user");
		
		if (loggedIn) {
			//get number of unlocked avatars based on general points
			int count = UserMethods.getNumberOfAvatars(user.getGeneralpoints());
			List<String> avatarUrls = UserMethods.getAvatars(count);
			
			//add to choose avatar page
			model.addAttribute("avatars", avatarUrls);
			
			//add user
			model.addAttribute("user", user);
		}
		
		//add logged in
		model.addAttribute("loggedin", loggedIn);
		
		return "choose-avatar";
		
	}
	
	
	
	//Submit avatar chose
	@RequestMapping("/avatar/choose")
	public String pickAvatar(
			@RequestParam("av") String url,
			Model model) {
		boolean loggedIn = Methods.checkLogin(session);
		User user = (User)session.getAttribute("user");
		
		//check if they have default user image
		boolean avDefault = false;
		
		//default url
		String urlDefault = "/settings";
		
		if (loggedIn) {
			
			//check for default, which means they are new
			if (user.getAvatar().equals("https://i.imgur.com/wtdETC3.png")) {
				urlDefault ="/questionaire";
			}
			
			user.setAvatar(url);
			//save user
			userRepo.save(user);
		}
		
		//add logged in
		model.addAttribute("loggedin", loggedIn);
		
		
		//if their avatar was a default to start, take them to questionaire page
		
		return "redirect:" + urlDefault;
		
	}
	
	
	
	//MESSAGING (2 person)
	
	//See all your messages
	@RequestMapping("/messages")
	public String messages(Model model) {
		
		boolean loggedIn = Methods.checkLogin(session);
		User user = (User)session.getAttribute("user");
		
		
		if (!loggedIn) {
			return "redirect:/login";
		}
		//Get list of messages connected to user
		//First get list of all
		List<UserMessage> allMessages = userMessageRepo.findAll();
		//Now search list for user id and add to new message set
		//First start with storing messages, then compare them to each other to make conversations
		List<Conversation> conversations = new ArrayList<>();
		for (UserMessage message: allMessages) {
			if (message.getReceiverId() == user.getId() ||
					message.getSenderId() == user.getId()) {
				User f = null;
				if (message.getReceiverId() == user.getId()) {
					Optional<User> temp = userRepo.findById(message.getSenderId());
					f = temp.get();
				} else {
					Optional<User> temp = userRepo.findById(message.getReceiverId());
					f = temp.get();
				}
				
				boolean exists = false;
				int isRead = 1;
				if (message.getIsRead() ==0) {
					isRead = 0;
				}
				
				System.out.println(isRead);
				
				Conversation newConvo = new Conversation(message.getId(), f, message.getReceiverId(), message.getMessage(),
						message.getDatetime(), isRead);
				System.out.println("Test message: " + message.getMessage());
				System.out.println("Test new convo: " + newConvo.getAbridgedMsg());
				
				
				for (Conversation con: conversations) {
					if (con.getFriend().getId() == f.getId()) {
						exists = true;
					}
				}
				if (!exists) {
					conversations.add(newConvo);
				} else {
					for (Conversation con: conversations) {
						if (con.getFriend().getId() == f.getId() &&
								con.getNewestDatetime().isBefore(message.getDatetime())) {
							conversations.remove(con);
							conversations.add(newConvo);
						}
					}
					
					
				}
				
			}
			
		}
		
		//Add length of conversation list to model
		model.addAttribute("length", conversations.size());
		
		//Add conversation list to page
		model.addAttribute("conversations", conversations);
		
		
		model.addAttribute("loggedin", loggedIn);
		//if logged in, add user to page
		if (loggedIn) {
			model.addAttribute("user", user);
		}
		
		return "user-messages";
		
	}
	
	//See messages between you and a friend/mutual friend
	@RequestMapping("/message")
	public String messageFriend(
			@RequestParam(value = "id") Long id,
			@RequestParam(value="begin", required=false) Integer begin,
			@RequestParam(value="end", required=false) Integer end,
			Model model) {
		
		boolean loggedIn = Methods.checkLogin(session);
		User user = (User)session.getAttribute("user");
		
		//if they're not logged in, redirect to login page
		if (!loggedIn) {
			return "login";
		}
		boolean areMessages = false;
		
		//Get other user by id
		Optional<User> temp = userRepo.findById(id);
		User friend = temp.get();
		
		//Check if friends with them
		boolean areFriends = UserMethods.checkIfFriends(user, friend);
		
		//Get messageRef
		String convoRef = UserMethods.getConversationRef(user, friend);
		
		//Get list of messages between users
		List<UserMessage> messages = userMessageRepo.findByConversationRef(convoRef);
		
		//list length
		int length = 0;
		
		//TODO When there's a chance, sort messages in reverse order by date
		//Create duplicate page where you can see all messages, or more messages
		
		//if list is not empty or null, then areMessages is true
		if (!messages.isEmpty() && messages != null) {
			areMessages = true;
			length = messages.size();
			
			for (UserMessage message: messages) {
				if (message.getReceiverId() == user.getId()) {
					message.setIsRead(1);
					userMessageRepo.save(message);
				}
				
			}
			
		}
		
		//Determine if user has unread messages or not
		//loop through messages, if there is anything unread besides this one, have user unread
		//otherwise, read
		List<UserMessage> all = userMessageRepo.findAll();
		int unread = 1;
		for (UserMessage m: all) {
			if (m.getIsRead() == 0 && 
					m.getReceiverId() == user.getId()) {
				unread = 0;
			}
			
		}
		//Set user to unread int
		user.setUnreadMessages(unread);
		userRepo.save(user);
		
		model.addAttribute("loggedin", loggedIn);
		//Add info about if they're friends
		model.addAttribute("arefriends", areFriends);
		//Add messages to page
		model.addAttribute("convo", messages);
		//Tell page if there are messages yet
		model.addAttribute("aremessages", areMessages);
		//Add friend to page
		model.addAttribute("friend", friend);
		//Add conversation length
		model.addAttribute("length", length);
		
		//if logged in, add user to page
		if (loggedIn) {
			model.addAttribute("user", user);
		}
		
		
		//Determine the page numbers
		//if the parameters are null, then set them to end
		if (end == null) {
			end = messages.size();
		}
		
		if (begin == null) {
			begin = messages.size() -5;
		}
		
		//if begin is less than 0, set it to 0
		if (begin < 0) {
			begin = 0;
		}
		
		//Add to model
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		
		
		return "message-user";
	}
	
	
	//Send message
	@PostMapping("/message/send")
	public String sendMessage(
			@RequestParam("message") String message,
			@RequestParam("user") Long senderId,
			@RequestParam("friend") Long receiverId
			) {
		
		//get user by id
		Optional<User> temp1 = userRepo.findById(senderId);
		User user = temp1.get();
		
		//get friend by id
		Optional<User> temp2 = userRepo.findById(receiverId);
		User friend = temp2.get();
		
		//get conversation ref
		String ref = UserMethods.getConversationRef(user, friend);
		
		//Create datetime
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String pattern = "MMM dd, yyyy HH:mm:ss.SSSSSSSS";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String timestampString = new SimpleDateFormat(pattern).format(timestamp);
		LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timestampString));
		
		//create new user message
		UserMessage newMessage = new UserMessage(ref, senderId, receiverId, localDateTime, message);
		
		//save message to repo
		userMessageRepo.save(newMessage);
		
		//set user to unread messages
		friend.setUnreadMessages(0);
		userRepo.save(friend);
		
		//loop through messages, if there is anything unread besides this one, have user unread
		//otherwise, read
		List<UserMessage> messages = userMessageRepo.findByConversationRef(UserMethods.getConversationRef(user, friend));
		int unread = 1;
		for (UserMessage m: messages) {
			if (m.getIsRead() == 0 && 
					m.getReceiverId() == user.getId()) {
				unread = 0;
			}
			
		}
		//Set user to unread int
		user.setUnreadMessages(unread);
		userRepo.save(user);
		
		
		return "redirect:/message?id=" + receiverId;
	}
	
	
	
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
	
	@RequestMapping("/friends") 
	public String friendList(
			@RequestParam(value="id") long id,
			Model model) {
		
		User user = (User)session.getAttribute("user");
		
		Optional<User> optUser = userRepo.findById(id);
		User profileUser = optUser.get();
		
		boolean loggedIn = Methods.checkLogin(session);
		boolean letSee = false;
		
		//if the id matches the session user id, let them see the list
		if (user != null && user.getId() == id) {
			letSee = true;
		}
		
		//Get list of user's friends ids
		List<String> friendIds = new ArrayList<>();
		try {
			friendIds = UserMethods.idStringToList(profileUser.getFriends());
		} catch (Exception e) {
			
		}
		
		//Turn list into list of their friends
		List<User> friends = new ArrayList<>();
		for (String s: friendIds) {
			if (!friends.isEmpty() || friends != null || !friends.get(0).equals("")) {
				try {
					long num = Long.parseLong(s);
					
					Optional<User> opt = userRepo.findById(num);
					User temp = opt.get();
					
					friends.add(temp);
				} catch (Exception e) {
					
				}
				
			}
			
		}
		
		//See if session user is in friend list
		for (User friend: friends) {
			if (friend.getId() == user.getId()) {
				letSee = true;
			}
		}
		
		//Tell jsp whether or not profile user has friends
		if (friends.isEmpty() || friends == null || friends.get(0).equals("")) {
			model.addAttribute("friends", false);
		} else {
			model.addAttribute("friends", true);
		}
		
		
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("list", friends);
		model.addAttribute("profile", profileUser);
		
		
		//If user is sneaky and isn't supposed to see the list, redirect to homepage
		if (letSee) {
			return "friend-list";
		} else {
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
		boolean canComment=false;
		boolean areComments = false;
		
		
		
		
		
		//get all saved items to put on profile, along with boolean on whether it exists
		
		//set booleans to false, will later determine if any category is posted on profile
		boolean areRecords = false;
		boolean areAffirmations = false;
		boolean areExercises = false;
		boolean areArticles = false;
		
		//Create Lists
		List<Record> records = recordRepo.findByUserId(id);
		List<FavAffirmation> affirmations = affirmationRepo.findByUserId(id);
		List<FavExercises> exercises = exerciseRepo.findByUserId(id);
		List<FavArticle> articles = articleRepo.findByUserId(id);
		
		//Create empty lists to store posted items
		List<Record> postedRecords = new ArrayList<>();
		List<FavAffirmation> postedAffirmations = new ArrayList<>();
		List<FavExercises> postedExercises = new ArrayList<>();
		List<FavArticle> postedArticles = new ArrayList<>();
		
		//Now loop through all beginning lists, store anything in new list that has onProfile = 1
		for (Record r: records) {
			if (r.getOnProfile() == 1) {
				postedRecords.add(r);
				areRecords = true;
			}
		}
		
		for (FavAffirmation a: affirmations) {
			if (a.getOnProfile() == 1) {
				postedAffirmations.add(a);
				areAffirmations = true;
			}
		}
		
		for (FavExercises e: exercises) {
			if (e.getOnProfile() == 1) {
				postedExercises.add(e);
				areExercises = true;
			}
		}
		
		for (FavArticle a: articles) {
			if (a.getOnProfile() == 1) {
				postedArticles.add(a);
				areArticles = true;
			}
		}
		
		//Now add elements to profile page
		model.addAttribute("records", postedRecords);
		model.addAttribute("affirmations", postedAffirmations);
		model.addAttribute("exercises", postedExercises);
		model.addAttribute("articles", postedArticles);
		
		model.addAttribute("arerecords", areRecords);
		model.addAttribute("areaffirmations", areAffirmations);
		model.addAttribute("areexercises", areExercises);
		model.addAttribute("arearticles", areArticles
				
				
				);
		
		
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
		
		List<String> friends = UserMethods.idStringToList(profileUser.getFriends());		
		
		if(loggedUser != null) {
			
			for(String ids: friends) {
				
				if(loggedUser.getId().toString().equals(ids)) {
					canComment = true;
				}
			}	
		}
		
		//Also check if the profile id equals the user id. If it does, let them comment.
		if (loggedUser.getId() == profileUser.getId()) {
			canComment = true;
		}
		
		List<ProfileComments> comments = new ArrayList<>();
		try {
		comments = profileCommentsRepo.findByProfileId(id);
		}catch(NullPointerException e) {
			
		}
		
		if (comments != null && comments.size() != 0) {
			areComments = true;
		}
		
		//Are we including Achievements still in the Use Profile?
		List<Achievements> achieve = achievementsRepo.findAchievementsByUserId(loggedUser.getId());
		
	
		model.addAttribute("achieve",achieve);
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("profileuser", profileUser);
		model.addAttribute("isfriend", isFriend);	
		model.addAttribute("isrequested", isRequested);
		model.addAttribute("acceptrequest", acceptRequested);
		model.addAttribute("canComment",canComment);
		model.addAttribute("arecomments", areComments);
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
						new ProfileComments(text,user.getId() , date, profileId, userRepo );
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
	
	//Post item to profile
	@RequestMapping("/post")
	public String postFavorite(
			@RequestParam("type") String type,
			@RequestParam("id") Long id,
			@RequestParam(name = "url") String url,
			Model model
			) {
		
		
		
		//figure out what type it is
		if (type.equals("record")) {
			
			//If it's this type, locate it by its id
			Optional<Record> r= recordRepo.findById(id);
			Record record = r.get();
			//Change onProfile to 1 to represent yes
			record.setOnProfile(1);
			//Now save it to database
			recordRepo.save(record);
			
		} else if (type.equals("affirmation")) {
			
			//If it's this type, locate it by its id
			Optional<FavAffirmation> a = affirmationRepo.findById(id);
			FavAffirmation affirmation = a.get();
			//Change onProfile to 1 to represent yes
			affirmation.setOnProfile(1);
			//Now save it to database
			affirmationRepo.save(affirmation);
			
		} else if (type.equals("exercise")) {
			
			//If it's this type, locate it by its id
			Optional<FavExercises> e = exerciseRepo.findById(id);
			FavExercises exercise = e.get();
			//Change onProfile to 1 to represent yes
			exercise.setOnProfile(1);
			//Now save it to database
			exerciseRepo.save(exercise);
			
		} else if (type.equals("article")) {
			
			//If it's this type, locate it by its id
			Optional<FavArticle> a = articleRepo.findById(id);
			FavArticle article = a.get();
			//Change onProfile to 1 to represent yes
			article.setOnProfile(1);
			//Now save it to database
			articleRepo.save(article);
			
		}
		
		return "redirect:" + url;
	}
	
	
	//Remove post item from profile
	@RequestMapping("/post/remove")
	public String postRemove(
			@RequestParam("type") String type,
			@RequestParam("id") Long id,
			@RequestParam(name = "url") String url,
			Model model
			) {
		
		
		//figure out what type it is
		if (type.equals("record")) {
			
			//If it's this type, locate it by its id
			Optional<Record> r= recordRepo.findById(id);
			Record record = r.get();
			//Change onProfile to 1 to represent yes
			record.setOnProfile(0);
			//Now save it to database
			recordRepo.save(record);
			
		} else if (type.equals("affirmation")) {
			
			//If it's this type, locate it by its id
			Optional<FavAffirmation> a = affirmationRepo.findById(id);
			FavAffirmation affirmation = a.get();
			//Change onProfile to 1 to represent yes
			affirmation.setOnProfile(0);
			//Now save it to database
			affirmationRepo.save(affirmation);
			
		} else if (type.equals("exercise")) {
			
			//If it's this type, locate it by its id
			Optional<FavExercises> e = exerciseRepo.findById(id);
			FavExercises exercise = e.get();
			//Change onProfile to 1 to represent yes
			exercise.setOnProfile(0);
			//Now save it to database
			exerciseRepo.save(exercise);
			
		} else if (type.equals("article")) {
			
			//If it's this type, locate it by its id
			Optional<FavArticle> a = articleRepo.findById(id);
			FavArticle article = a.get();
			//Change onProfile to 1 to represent yes
			article.setOnProfile(0);
			//Now save it to database
			articleRepo.save(article);
			
		}
			
			return "redirect:" + url;
		}
	
	
	
	//form for submitting achievements to be displayed
	//TO DO add points and credit system
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
		
		model.addAttribute("loggedin", loggedIn);
		if (loggedIn) {
			User user = (User)session.getAttribute("user");
			model.addAttribute("user", user);
		}

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
			//set default image url:
			user.setAvatar("https://i.imgur.com/wtdETC3.png");
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
			
			return "redirect:/avatar";

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
