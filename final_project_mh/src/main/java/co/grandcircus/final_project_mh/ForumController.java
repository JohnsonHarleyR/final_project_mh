package co.grandcircus.final_project_mh;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;

@Controller
public class ForumController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userRepo;
	
	
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
			//@RequestParam("id") Long id,
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
	
	
	

}
