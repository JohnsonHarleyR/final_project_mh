package co.grandcircus.final_project_mh;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.final_project_mh.Forum.Discussion;
import co.grandcircus.final_project_mh.Forum.DiscussionDao;
import co.grandcircus.final_project_mh.Forum.PostsDao;
import co.grandcircus.final_project_mh.Forum.ThreadDao;

import co.grandcircus.final_project_mh.Forum.Posts;
import co.grandcircus.final_project_mh.Forum.Thread;

import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;

@Controller
public class ForumController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userRepo;
	
	//Add discussion repo
	@Autowired
	private DiscussionDao discussionRepo;
	//Add thread repo
	@Autowired
	private PostsDao postsRepo;
	
	@Autowired
	private ThreadDao threadRepo;
	
	@RequestMapping("/forum")
	public String mainForum(Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		// for now this gets the info of the two announcements that are already hard coded
		// into SQL but eventually we will be able to add more discussions from admin user 
		// accounts
		// that can be done by using a c for each after each disccussion is placed in a 
		// list 
		
		//creating a list of discussions and adding it to the model
		List<Discussion> allDiscussions = discussionRepo.findAll();
		model.addAttribute("discussions", allDiscussions);
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		return "forum-main";
	}
	
	//individual discussion and it's threads 
	@RequestMapping("/forum/discussion")
	public String forumDiscussion(
			@RequestParam("id") Long id,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		//for the id
		model.addAttribute("id",id);
		
		//all the threads associated with the passed discussion id
		List<Thread> allThreads = threadRepo.findByDiscussionId(id);
		model.addAttribute("threads", allThreads);
		
		// the current discussion so that the values can be used
		Discussion discussion = discussionRepo.findById(id).orElse(null);
		model.addAttribute("discussion",discussion);
		
		// need to add a query to the thread DAO that counts the thread count for the 
		// associated discussionid
		
		List<Posts> allPosts = postsRepo.findAll();
		model.addAttribute("posts",allPosts);
		
		//also need to figure out how to get the latest thread or post to appear
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		
		return "forum-discussion";
	}
	
	//individual threads inside discussion
	@RequestMapping("/thread")
	public String forumThread(
			@RequestParam("id") Long threadId,
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
		
		//Get Main thread and add model
		Thread thread = threadRepo.findById(threadId).orElse(null);
		model.addAttribute("thread",thread);
		
		// adding discussion to the model
		Discussion discussion = discussionRepo.findById(thread.getDiscussionId()).orElse(null);
		model.addAttribute("discussion",discussion);
		
		
		List<Posts> posts = postsRepo.findByThreadId(threadId);
		model.addAttribute("posts",posts);
		
		return "forum-thread";
	}
	
	//Add thread to discussion
	@RequestMapping("/thread/add")
	public String addThread(
			@RequestParam("id") Long discussionId,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		Discussion discussion = discussionRepo.findById(discussionId).orElse(null);
		
		//add discussion to model
		model.addAttribute("discussion", discussion);
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		//If user is logged in, add user to model
		if (loggedIn) {
			model.addAttribute("user", user);
		}
		
		return "forum-add-thread";
		
	}
	
	//add new post to the thread
	@RequestMapping("/forum/discussion/add-post")
	public String addThreadPost(
			@RequestParam("id") Long threadId,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
	
		// for the thread 
		model.addAttribute("threadId",threadId);
		
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		//If user is logged in, add user to model
		if (loggedIn) {
			model.addAttribute("user", user);
		}
		
		return "forum-add-post";
		
	}
	
	
	//Add thread to discussion

	@PostMapping("/thread/add/submit")
	public String submitThread(
			@RequestParam ("discussionId") Long discussionId,
			@RequestParam ("threadTitle") String threadTitle,
			@RequestParam ("comment") String message,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//Create timestamp for thread and first post
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String pattern = "MMM dd, yyyy HH:mm:ss.SSSSSSSS";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String timestampString = new SimpleDateFormat(pattern).format(timestamp);
		LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timestampString));
		

		//Create new thread, put into thread table
		Thread thread = new Thread(threadTitle,discussionId,user.getUsername(), localDateTime);
		
		//save to thread repo
		threadRepo.save(thread);
		
		//creating new post to show on the thread
		Posts post = new Posts(user.getUsername(), thread.getId(), localDateTime,
				message, discussionId);
		
		postsRepo.save(post);
		
		return "redirect:/thread?id=" + thread.getId() ;
		
	}
	
	// add post to thread
	@PostMapping("/post/add/submit")
	public String submitpost(
			@RequestParam ("threadId") Long threadId,
			@RequestParam ("comment") String message,
			Model model) {
		
		//Get session user
		User user = (User)session.getAttribute("user");
		
		//Create timestamp for thread and first post
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String pattern = "MMM dd, yyyy HH:mm:ss.SSSSSSSS";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String timestampString = new SimpleDateFormat(pattern).format(timestamp);
		LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timestampString));
		//create a thread so you can get the discussion id
		Thread thread = threadRepo.findById(threadId).orElse(null);
		Long discussionId = thread.getDiscussionId();
		//Create new thread, put into discussion table
		Posts post  = new Posts(user.getUsername(), threadId, localDateTime, message, discussionId);
		//save to post to repo
		postsRepo.save(post);
		
		return "redirect:/thread?id=" + threadId ;
		
	}

		
	//delete post
	@RequestMapping("/post/delete")
	public String deletePost(@RequestParam("id") Long postId) {
		
	
		
		Posts post = postsRepo.findById(postId).orElse(null);
		
		Long threadId = post.getThreadId();
		postsRepo.deleteById(postId);
	return "redirect:/thread?id=" + threadId ;	
	}
	
	

}
