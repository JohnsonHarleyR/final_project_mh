package co.grandcircus.final_project_mh;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.el.parser.ParseException;

import co.grandcircus.final_project_mh.Gamification.Achievements;
import co.grandcircus.final_project_mh.Gamification.AchievementsRepo;
import co.grandcircus.final_project_mh.Gamification.Challenge;
import co.grandcircus.final_project_mh.Gamification.ChallengeDao;
import co.grandcircus.final_project_mh.Gamification.ChallengeList;
import co.grandcircus.final_project_mh.Gamification.ChallengeListDao;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;

//This controller manages most the views involved with challenge implementation

@Controller
public class GameController {
	
	@Autowired private AchievementsRepo achievementsRepo;
	@Autowired private ChallengeDao ChallengeRepo;
	@Autowired private ChallengeListDao ChallengeListRepo;
	@Autowired private HttpSession session;
	@Autowired private UserDao userRepo;
	
	
	@RequestMapping("/invest-points")
	public String challengeForm(Model model) {
		
		boolean enoughPoints = true;
		boolean loggedIn = Methods.checkLogin(session);
		User user = (User)session.getAttribute("user");
		
		model.addAttribute("enoughPoints", enoughPoints);
		model.addAttribute("loggedin", loggedIn);
		
		List<co.grandcircus.final_project_mh.Gamification.Challenge> challenge;
		
	    //adds total points of users accomplishments
		if(user != null) {
		
		challenge = ChallengeRepo.findChallengeByUserId(user.getId());
		long points = 0;
		for(int i =0; i < challenge.size(); i++)
		{points = points + challenge.get(i).getPoints();
		//System.out.print(points);
		}
		}
		
	return "invest-points";
	}
	
	
	@RequestMapping("/submit-challenge")
	
	public String submitChallenge(@RequestParam (value="category") String category, 
			@RequestParam(value = "description")String description,
			@RequestParam(value="name")String name, @RequestParam(value = "points_req") Long points_req, 
			@RequestParam(value = "prize_url")String prize_url, Model model){
		
		    boolean loggedIn = Methods.checkLogin(session);
		    User user=(User)session.getAttribute("user");
		   // Long user_id = user.getId(); 
		  
		    ChallengeList challengeList = new ChallengeList();
		    
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Date date=new Date(timestamp.getTime());
		    
		    challengeList.setCategory(category);
		    challengeList.setDatetime(date);
		    challengeList.setDescription(description);
		    challengeList.setName(name);
		    challengeList.setPointsReq(points_req);
		    challengeList.setPrizeUrl(prize_url);
		    challengeList.setUser(user);
		    
		    boolean enoughPoints = true;
		    
		    if (user.getPoints() >= 15) {
				Methods.addPoints(-15, user, userRepo);	
				 ChallengeListRepo.save(challengeList);
			}
			else {enoughPoints = false;}

		  //need to include user into the challenge as created by
		    model.addAttribute("loggedin",loggedIn);
			model.addAttribute("enoughPoints", enoughPoints);
		  
		
		return "invest-points";
	}

	//form for submitting achievements to be displayed
		//TO DO add points and credit system
		@RequestMapping("/submit/achievement")
		public String achievements(@RequestParam("achievementName") String achievementName,
				@RequestParam("achievementDescription") String achievementDescription,
				@RequestParam("achievementDate") String achievementDate,
				Model model) 
		{
			 boolean loggedIn = Methods.checkLogin(session);
			
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			  try {
		            Date date = formatter.parse(achievementDate);
		        } catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
			User user = (User)session.getAttribute("user");
			Achievements achievements = new Achievements();
			achievements.setUser(user);
		//	achievements.setDate(achievementDate);
			achievements.setDescription(achievementDescription);
			achievements.setName(achievementName);		
            
			boolean enoughPoints = true;
			
			if (user.getPoints() >= 10) {
				Methods.addPoints(-10, user, userRepo);	
			    achievementsRepo.save(achievements); 
			}
			else {enoughPoints = false;}
			
			//need to include user into the challenge as created by
		    model.addAttribute("loggedin",loggedIn);
			model.addAttribute("enoughPoints", enoughPoints);
			return "invest-points";
		}	
		
		
		@RequestMapping("/challenges")
		
		public String challengeList(Model model) {
			
			    User user;
			    boolean loggedIn = Methods.checkLogin(session);
			    user=(User)session.getAttribute("user");
			    Long user_id = user.getId(); 
			   
			   // List<ChallengeList> challengeList = ChallengeListRepo.findChallengeListByUserId(user_id);
			    List<Challenge> userChallengeList = ChallengeRepo.findChallengeByUserId(user_id);
			    List<ChallengeList> challengeList = ChallengeListRepo.findAll();            
			       
			    //need to include user into the challenge as created by
			    model.addAttribute("loggedin",loggedIn);

			    model.addAttribute("userChallengeList",userChallengeList);
			    model.addAttribute("challengeList",challengeList);
//			    String category = challengeList.getCategory();
//			    Date datetime = challengeList.getDatetime();
//			    String Description = challengeList.getDescription();
//			    String Name = challengeList.getName();
//			    Long pointsReq  =  challengeList.getPointsReq();
//			    String prizeUrl =  challengeList.getPrizeUrl(); 
			
			return "challenges";
		}
		
@RequestMapping("/challenge-complete")
		
		public String challengeComplete(@RequestParam("complete") boolean complete,
				@RequestParam("id") Long id, Model model){
	
			    User user;
			    user=(User)session.getAttribute("user");
			   // Long user_id = user.getId();
			    
			  //completed challenge List per user
			  //refer to ChallengeDao and Challenge POJO
			  //List<Challenge> userCompleteList = ChallengeRepo.findCompleteByUserId(user_id);
			    
			  Challenge userchallenge = ChallengeRepo.findCompleteBychallengeid(id);
			  String category = userchallenge.getCategory();
			 
			  Integer bodyPoints = user.getBodypoints();
			  Integer mindPoints = user.getMindpoints();
			  Integer soulPoints = user.getSoulpoints();
			  Integer userPoints = user.getPoints();
			  Integer points = (int) (long) userchallenge.getPoints();
			  
			  String body = "body";
			  String mind = "mind";
			  String soul = "soul";
			  if (complete == true && category.equals(body)) {user.setBodypoints(bodyPoints + points);user.setPoints(userPoints + points);}
			  else if (complete == true && category.equals(mind)) {user.setMindpoints(mindPoints + points); user.setPoints(userPoints + points);}
			  else if (complete == true && category.equals(soul)) {user.setSoulpoints(soulPoints + points); user.setPoints(userPoints + points);} 
			  else if (complete == false && category.equals(body) && (user.getPoints()- points) >=0) {user.setBodypoints(bodyPoints - points);user.setPoints(userPoints - points);}
			  else if (complete == false && category.equals(mind) && (user.getPoints()- points) >=0) {user.setMindpoints(mindPoints - points);user.setPoints(userPoints - points);}
			  else if (complete == false && category.equals(soul) && (user.getPoints()- points) >=0) {user.setSoulpoints(soulPoints - points);user.setPoints(userPoints - points);}
			  else {System.out.print("broke");}
			  
			  
			  userRepo.save(user);
			  userchallenge.setComplete(complete);    
			  ChallengeRepo.save(userchallenge);
			   
			return "redirect:/challenges";
		}
		
		
		@RequestMapping("/select-challenge")
		public String challengeList(@RequestParam("id") Long id,
				Model model) {
			
			User user;
			boolean loggedIn = Methods.checkLogin(session);
		    user=(User)session.getAttribute("user");
		    Long user_id = user.getId();
			
		ChallengeList challengeList = ChallengeListRepo.findByChallengeListId(id);
			
		 Challenge challenge =  new Challenge();
		 
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 Date date=new Date(timestamp.getTime()); 
		 boolean complete = false;
		 
		 Long challengelistid = challengeList.getChallengeListId();
		 Long points = challengeList.getPointsReq();              	
		 String category = challengeList.getCategory();         
		 String prizeUrl = challengeList.getPrizeUrl();              
		 String name = challengeList.getName();
		 String description = challengeList.getDescription();
		    
		    challenge.setDate(date);
		    challenge.setComplete(complete);
		    challenge.setChallengelistid(challengelistid);
		    challenge.setName(name);
		    challenge.setDescription(description);
		    challenge.setCategory(category);
		    challenge.setPoints(points);
		    challenge.setPrizeUrl(prizeUrl);
		    challenge.setUser(user);
		    
		    System.out.println(prizeUrl);
		    
		    ChallengeRepo.save(challenge);
		    
			return "redirect:/challenges";
		}
		
		//Delete Accepted Challenge
		//pass in Challenge Id to param for deletion
		
		@RequestMapping("/delete/acceptedchallenge")
		public String deleteAchievement(@RequestParam("id") Long id, 
				Model model) { 
		
			boolean loggedIn = Methods.checkLogin(session);
			User user = (User)session.getAttribute("user");
			
			ChallengeRepo.deleteById(id);
			model.addAttribute("loggedin",loggedIn);
			model.addAttribute("user",user);
		//	if (user.getPoints() - 10 >0 ||user.getPoints() - 10 != 0) {				Methods.addPoints(-10, user, userRepo);
		//	}
		
			return "redirect:/challenges";
		}	
		
		
		
		
		//Delete Achievement
		//pass in Achievement Id to param for deletion
		
//		@RequestMapping("/delete/achievement")
//		public String deleteAchievement(Model model) { 
//			
//		    Long id = (long) 1;
//		
//			User user = (User)session.getAttribute("user");
//			
//			AchievementsRepo.deleteById(id);
//			
//			if (user.getPoints() - 10 >0 ||user.getPoints() - 10 != 0) {
//				Methods.addPoints(-10, user, userRepo);
//			}
//			
//			return "redirect:" + url;
//		}
	
}
