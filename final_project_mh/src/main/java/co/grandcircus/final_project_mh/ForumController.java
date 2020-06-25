package co.grandcircus.final_project_mh;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
		model.addAttribute("user", user);
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		// for now this gets the info of the two announcements that are already hard coded
		// into SQL but eventually we will be able to add more discussions from admin user 
		// accounts
		// that can be done by using a c for each after each disccussion is placed in a 
		// list 
		
		//creating a list of discussions and adding it to the model
		List<Discussion> allDiscussions = discussionRepo.findAll();
		
		if (allDiscussions == null || allDiscussions.size() == 0) {
			//If list is empty, pre-populate with the following:
			allDiscussions.add(new Discussion("announcement", "All the latest news from the admins about this site.",
					"Announcements", "welcome", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "Experiencing any bugs or problems on the site? Tell us here!",
					"Site Feedback", "welcome", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "A great place to say hello and meet other users.",
					"Introduce Yourself", "welcome", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "What do you like to do in your free time?",
					"Hobbies", "general", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "Discuss music, movies, books, games, etc.",
					"Entertainment", "general", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "Talk about what's happening in the news today.",
					"Current Events", "general", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "Talk about anything that "
					+ "doesn't fall under any other discussion topic.",
					"Miscellaneous", "general", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "We all need help sometimes. This is a safe place"
					+ " to talk about your struggles and help other people who might be going through the same thing.",
					"Life Issues & Advice", "mental health", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "A safe place to talk about a diagnosis. You might find someone"
					+ " else with the same thing.",
					"Diagnoses", "mental health", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "One of the hardest things to deal with is stigma. How has "
					+ "stigma affected you, and how have you learned to cope with it?",
					"Stigma", "mental health", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "What helps you get through when the going gets rough?",
					"Strategies to Cope", "mental health", "JohnsonHarleyR"));
			allDiscussions.add(new Discussion("regular", "Sometimes, what a person really needs is hope from know seeing "
					+ "that someone else struggled like them and managed to survive. Hold up a candle in the darkness.",
					"Stories of Recovery", "mental health", "JohnsonHarleyR"));
			
			//save all these discussions to the repo
			for (Discussion discuss: allDiscussions) {
				discussionRepo.save(discuss);
			}
			
		}
		
		//Get list of all posts for the jsp
		List<Posts> allPosts = postsRepo.findAll();
		model.addAttribute("posts", allPosts);
		
		if (loggedIn) {
			model.addAttribute("user");
		}
		
		
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
		
		//temporary
		for (Posts post: allPosts) {
			if (post.getMessage().length() > 40) {
				post.setAbridgedMsg(post.getMessage().substring(0, 39));
			} else {
				post.setAbridgedMsg(post.getMessage());
			}
		}
		
		//also need to figure out how to get the latest thread or post to appear
		//for the header
		model.addAttribute("loggedin", loggedIn);
		
		
		return "forum-discussion";
	}
	
	//For the admin to create a discussion
	@RequestMapping("/discussion/create") 
	public String createDiscussion(Model model) {
		boolean canAdd = false;
		//double check here for security, make sure logged in user is an admin
		//Get session user
		User user = (User)session.getAttribute("user");
		String userStatus = user.getStatus();	
		
		//adding the user to the model
		model.addAttribute("user", user);
		//for the header
		boolean loggedIn = Methods.checkLogin(session);
		
		if (loggedIn = true && userStatus.equalsIgnoreCase("admin")) {
			canAdd = true;
		}
		model.addAttribute("canAdd", canAdd);
		model.addAttribute("loggedin", loggedIn);
		
		
		return "forum-add-discussion";
	}
	
	
	@PostMapping("/discussion/create")
	public String submitDiscussion(@RequestParam(value = "post-type") String postType,
			@RequestParam(value="description") String description, @RequestParam(value="topic") String topic,
			@RequestParam(value="username") String username, @RequestParam(value = "tag") String tag) {
		
		if (!postType.equalsIgnoreCase("admin")){
			postType = "normal";
		}
		
		Discussion discussion = new Discussion(postType, description, topic, tag, username);
		discussionRepo.save(discussion);
		
		
		return "redirect:/forum"; 
		
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
		
		
		// adding discussion to the model
		Discussion discussion = discussionRepo.findById(thread.getDiscussionId()).orElse(null);
		
		
		
		List<Posts> posts = postsRepo.findByThreadId(threadId);
		
		
		//set number of posts for the thread
		Long num = (long)posts.size();
		thread.setNumberOfPosts(num);
		threadRepo.save(thread);
		
		//Store number of posts, add to jsp
		int numPosts = posts.size();
		
		model.addAttribute("num", numPosts);
		model.addAttribute("thread",thread);
		model.addAttribute("posts",posts);
		model.addAttribute("discussion",discussion);
		
		
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
			@RequestParam(name="type", required=false) String postType,
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
		Thread thread = new Thread(threadTitle,discussionId,user.getUsername(),
				localDateTime, user.getId());
		
		//save to thread and post repos
				threadRepo.save(thread);
				
		//Grab the discussion too
		Optional<Discussion> discuss = discussionRepo.findById(thread.getDiscussionId());
		Discussion discussion = discuss.get();
		
		//Figure out how many threads are in the discussion
		List<Thread> t = threadRepo.findAll();
		List<Thread> threads = new ArrayList<>();
		for (Thread th: t) {
			if (th.getDiscussionId() == discussion.getId()) {
				threads.add(th);
			}
		}
		Long numOfThreads = (long)threads.size();
		//save that number to discussion
		discussion.setNumberOfTopics(numOfThreads);
		
		//creating new post to show on the thread
		Posts post = new Posts(user.getUsername(), thread.getId(), localDateTime,
				message, discussionId, user);
		
		try {
			post.setPostType(postType);
		} catch (Exception e) {
			
		}
		
		postsRepo.save(post);
		
		//set latest post id of discussion to new post
		discussion.setLastTopicPostId(post.getId());
		
		//now save discussion to the repo
		discussionRepo.save(discussion);
		
		
		System.out.println("Post id: " + post.getId());
		
		
		//now add the new post id to the thread's latest post id
		thread.setLastPostId(post.getId());
		
		//save to thread and post repos
				threadRepo.save(thread);
		
		
		
		
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
		Posts post  = new Posts(user.getUsername(), threadId, localDateTime, message,
				discussionId, user);
		
		
		postsRepo.save(post);
		//now add the new post id to the thread's latest post id
		thread.setLastPostId(post.getId());
		
		List<Posts> posts = postsRepo.findByThreadId(thread.getId());
		//set number of posts for the thread
		Long num = (long)posts.size();
		thread.setNumberOfPosts(num);
		
		//Also save to latest in discussion
		Optional<Discussion> discuss = discussionRepo.findById(discussionId);
		Discussion discussion = discuss.get();
		discussion.setLastTopicPostId(post.getId());
		
		//Get list of all posts connected to discussion
		List<Thread> allThreads = threadRepo.findAll();
		Long counter = (long)0;
		for (Thread t: allThreads) {
			if (t.getDiscussionId() == discussion.getId()) {
				counter ++;
			}
		}
		discussion.setNumberOfTopics(counter);
		discussionRepo.save(discussion);
		
		
		//save to repo
		threadRepo.save(thread);
		
		return "redirect:/thread?id=" + threadId ;
		
	}

		
	//delete post
	@RequestMapping("/post/delete")
	public String deletePost(@RequestParam("id") Long postId) {
		
	
		
		Posts post = postsRepo.findById(postId).orElse(null);
		
		Long threadId = post.getThreadId();
		postsRepo.deleteById(postId);
		
		//now we have to make sure the discussion won't show that thread booo
		
		//Get the thread
		Optional<Thread> t = threadRepo.findById(post.getThreadId());
		Thread thread = t.get();
		
		//Now get the discussion
		Optional<Discussion> di = discussionRepo.findById(thread.getDiscussionId());
		Discussion discussion = di.get();
		
		//Now get list of all posts
		List <Posts> allPosts = postsRepo.findAll();
		List<Posts> posts = new ArrayList<>();
		//Now loop through to find relevant posts
		for (Posts p: allPosts) {
			if (p.getDiscussionId() == discussion.getId()) {
				posts.add(p);
			}
		}
		//now figure out the largest post id
		Long largest = (long)0;
		for (Posts p: posts) {
			if (p.getId() > largest) {
				largest = p.getId();
			}
		}
		//store result inside discussion
		discussion.setLastTopicPostId(largest);
		
		//Now we need a list of all Threads by discussion id
		//(this sure is a lot just to display a little info in the forum lol)
		List<Thread> threads = threadRepo.findByDiscussionId(discussion.getId());
		//Count number of topics
		Long numTopics = (long)threads.size();
		//Add that number to discussion
		
		discussion.setNumberOfTopics(numTopics);
		//Save discussion
		discussionRepo.save(discussion);
		
		
	return "redirect:/thread?id=" + threadId ;	
	}
	
	//delete thread
		@RequestMapping("/thread/delete")
		public String deleteThread(@RequestParam("id") Long threadId,
				@RequestParam("d") Long discussId) {
			
			
			
			//also delete all related posts from database
			List<Posts>posts = postsRepo.findByThreadId(threadId);
			for (Posts post: posts) {
				postsRepo.delete(post);
			}
			
			
			//now we have to make sure the discussion won't show that thread booo
			
			//Get the thread
			Optional<Thread> t = threadRepo.findById(threadId);
			Thread thread = t.get();
			
			//Now get the discussion
			Optional<Discussion> di = discussionRepo.findById(thread.getDiscussionId());
			Discussion discussion = di.get();
			
			//Now get list of all posts
			List <Posts> allPosts = postsRepo.findAll();
			List<Posts> dPosts = new ArrayList<>();
			//Now loop through to find relevant posts
			for (Posts p: allPosts) {
				if (p.getDiscussionId() == discussion.getId()) {
					dPosts.add(p);
				}
			}
			//now figure out the largest post id
			Long largest = (long)0;
			for (Posts p: dPosts) {
				if (p.getId() > largest) {
					largest = p.getId();
				}
			}
			//store result inside discussion
			discussion.setLastTopicPostId(largest);
			
			//Now we need a list of all Threads by discussion id
			//(this sure is a lot just to display a little info in the forum lol)
			List<Thread> threads = threadRepo.findByDiscussionId(discussion.getId());
			//Count number of topics
			Long numTopics = (long)threads.size();
			//Add that number to discussion
			
			discussion.setNumberOfTopics(numTopics);
			//Save discussion
			discussionRepo.save(discussion);
			
			threadRepo.deleteById(threadId);
			
			
			
		return "redirect:/forum/discussion?id=" + discussId;	
		}
	
	

}
