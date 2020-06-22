package co.grandcircus.final_project_mh;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.final_project_mh.Forum.Discussion;
import co.grandcircus.final_project_mh.Forum.thread;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;

@Controller
public class ForumController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userRepo;
	
	//Add discussion repo
	
	//Add thread repo
	
	
	@RequestMapping("/forum")
	public String mainForum(Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		return "forum-main";
	}
	
	@RequestMapping("/forum/discussion")
	public String forumDiscussion(
			@RequestParam("id") Long id,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		
		return "forum-discussion";
	}
	
	//individual threads inside discussion
	@RequestMapping("/forum/thread")
	public String forumThread(
			//@RequestParam("id") Long threadId,
			//@RequestParam("discussion") Long discussionId,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		//If user is logged in, add user to model
		if (loggedIn) {
			model.addAttribute("user", user);
		}
		
		return "forum-thread";
	}
	
	//Add thread to discussion
	@RequestMapping("/thread/add")
	public String addThread(
			@RequestParam("d") Long discussionId,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		
		
		//add discussion id to model
		model.addAttribute("discussion", discussionId);
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		//If user is logged in, add user to model
		if (loggedIn) {
			model.addAttribute("user", user);
		}
		
		return "forum-add-thread";
		
	}
	
	//Add thread to discussion
	@PostMapping("/thread/add/submit")
	public String submitThread(
			@RequestParam ("discussion") Long discussionId,
			@RequestParam ("topic") String topic,
			@RequestParam ("thread") Long threadId,
			@RequestParam ("message") String message,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//Create timestamp for thread and first post
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		//Create new thread, put into discussion table
		Discussion newThread = new Discussion(); //pass parameters
		//save to discussion repo
		
		//Create new post in thread table using newly created thread id
		thread newPost = new thread(); //pass parameters
		//save to thread repo
		
		
		return "redirect: /thread?id=" + threadId;
		
	}
		
	//delete thread
	
	

}
