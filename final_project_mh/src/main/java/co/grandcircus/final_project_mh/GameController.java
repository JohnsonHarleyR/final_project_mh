package co.grandcircus.final_project_mh;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.final_project_mh.Gamification.Achievements;
import co.grandcircus.final_project_mh.Gamification.AchievementsRepo;
import co.grandcircus.final_project_mh.Gamification.ChallengeDao;
import co.grandcircus.final_project_mh.Gamification.ChallengeList;
import co.grandcircus.final_project_mh.Gamification.ChallengeListDao;
import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;

//test controller: made as a template to be implemented across app

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
		int points = 0;
		for(int i =0; i < challenge.size(); i++)
		{points = points + challenge.get(i).getPoints();
		System.out.print(points);
		}
		}
		
	return "invest-points";
	}
	
	@RequestMapping("/submit-challenge")
	
	public String submitChallenge(@RequestParam (value="category") String category, 
			@RequestParam(value = "description")String description,
			@RequestParam(value="name")String name, @RequestParam(value = "points_req") Long points_req, 
			@RequestParam(value = "prize_url")String prize_url, Model model){
		
		    User user;
		    user=(User)session.getAttribute("user");
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
		    
		    ChallengeListRepo.save(challengeList);
		    
		
		return "redirect:/invest-points";
	}

	//form for submitting achievements to be displayed
		//TO DO add points and credit system
		@RequestMapping("/submit/achievement")
		public String achievements(@RequestParam("achievementName") String achievementName,
				@RequestParam("achievementDescription") String achievementDescription,
				@RequestParam("achievementDate") Date achievementDate,
				Model model) 
		{
			
			User user = (User)session.getAttribute("user");
			Achievements achievements = new Achievements();
			achievements.setUser(user);
			achievements.setDate(achievementDate);
			achievements.setDescription(achievementDescription);
			achievements.setName(achievementName);		
            
			boolean enoughPoints = true;
			
			if (user.getPoints() >= 10) {
				Methods.addPoints(-10, user, userRepo);	
			    achievementsRepo.save(achievements); 
			}
			else {enoughPoints = false;}
			
			model.addAttribute("enoughPoints", enoughPoints);
			return "redirect:/invest-points";
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
